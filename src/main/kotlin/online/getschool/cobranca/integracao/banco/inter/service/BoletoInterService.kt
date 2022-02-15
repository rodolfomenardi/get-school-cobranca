package online.getschool.cobranca.integracao.banco.inter.service

import online.getschool.cobranca.dominio.dto.boleto.*
import online.getschool.cobranca.integracao.banco.BoletoService
import online.getschool.cobranca.integracao.banco.inter.adapter.ApiBoletoInterAdapter
import online.getschool.cobranca.integracao.banco.inter.dto.resposta.RespostaBoletoInter
import online.getschool.cobranca.integracao.banco.inter.dto.resposta.RespostaDescontoInter
import online.getschool.cobranca.integracao.banco.inter.dto.resposta.RespostaMensagemInter
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class BoletoInterService(private val apiBoletoInterAdapter: ApiBoletoInterAdapter) : BoletoService {
    companion object {
        private val logger = LoggerFactory.getLogger(BoletoInterService::class.java)
    }

    override fun emitir(boleto: Boleto): Boleto {
        TODO("Not yet implemented")
    }

    override fun recuperarBoletos(): Collection<Boleto> {
        TODO("Not yet implemented")
    }

    override fun recuperarBoleto(boletoId: String): Boleto {
        logger.debug("Recuperando boletos do banco inter")

        val boletoResponse = apiBoletoInterAdapter.recuperarBoleto(boletoId)
        return boletoResponse.let {
            parseBoleto(it, boletoId)
        }
    }

    private fun parseBoleto(
        boletoResponse: RespostaBoletoInter,
        boletoId: String
    ): Boleto {
        val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        return Boleto(
            seuNumero = boletoResponse.seuNumero.toInt(),
            valor = boletoResponse.valorNominal.toBigDecimal(),
            dataEmissao = LocalDate.parse(boletoResponse.dataEmissao, dateFormatter),
            dataVencimento = LocalDate.parse(boletoResponse.dataVencimento, dateFormatter),
            nossoNumero = boletoId,
            codigoBarras = boletoResponse.codigoBarras,
            linhaDigitavel = boletoResponse.linhaDigitavel,
            status = StatusEnum.valueOf(boletoResponse.situacao),
            motivoBaixa = boletoResponse.situacao,
            beneficiario = parseBeneficiario(boletoResponse),
            pagador = parsePagador(boletoResponse),
            descontos = parseDescontos(boletoResponse),
            mora = parseMora(boletoResponse),
            multa = parseMulta(boletoResponse),
            mensagens = parseMensagens(boletoResponse.mensagem)
        )
    }

    private fun parseBeneficiario(it: RespostaBoletoInter) = Beneficiario(
        nome = it.nomeBeneficiario,
        cnpj = it.cnpjCpfBeneficiario
    )

    private fun parsePagador(it: RespostaBoletoInter) = Pagador(
        tipoPessoa = if ("FISICA" == it.tipoPessoaPagador) TipoPessoaEnum.PESSOA_FISICA else TipoPessoaEnum.PESSOA_JURIDICA,
        nome = it.nomePagador,
        documento = it.cnpjCpfPagador.toLong(),
        email = it.emailPagador,
        endereco = parseEndereco(),
        telefone = parseTelefone(it)
    )

    private fun parseEndereco() = Endereco( //TODO: Consultar e preencher endereço
        logradouro = "",
        numero = 0,
        bairro = "",
        complemento = "",
        cidade = "",
        uf = "",
        cep = ""
    )

    private fun parseTelefone(it: RespostaBoletoInter) = Telefone(
        ddd = it.dddPagador.toInt(),
        numero = it.telefonePagador.toInt()
    )

    private fun parseMora(it: RespostaBoletoInter) = Mora(
        tipo = TipoMoraEnum.TAXA_MENSAL, //TODO: Ajustar o parse do tipo
        data = it.mora.data,
        taxa = it.mora.taxa.toBigDecimal(),
        valor = it.mora.valor.toBigDecimal()
    )

    private fun parseMulta(it: RespostaBoletoInter) = Multa(
        tipo = TipoMultaEnum.PERCENTUAL, //TODO: Ajustar o parser do tipo
        data = it.multa.data,
        taxa = it.multa.taxa.toBigDecimal(),
        valor = it.multa.valor.toBigDecimal()
    )

    private fun parseMensagens(mensagemResponse: RespostaMensagemInter): Collection<String> {
        val mensagens: MutableCollection<String> = mutableListOf()

        if (mensagemResponse.linha1.isNotBlank()) {
            mensagens.add(mensagemResponse.linha1)
        }

        if (mensagemResponse.linha2.isNotBlank()) {
            mensagens.add(mensagemResponse.linha2)
        }

        if (mensagemResponse.linha3.isNotBlank()) {
            mensagens.add(mensagemResponse.linha3)
        }

        if (mensagemResponse.linha4.isNotBlank()) {
            mensagens.add(mensagemResponse.linha4)
        }

        if (mensagemResponse.linha5.isNotBlank()) {
            mensagens.add(mensagemResponse.linha5)
        }

        return mensagens.toList()
    }

    private fun parseDescontos(boletoResponse: RespostaBoletoInter): Collection<Desconto> {
        val descontos: MutableCollection<Desconto> = mutableSetOf()
        if (this.temDesconto(boletoResponse.desconto1)) {
            descontos.add(parseDesconto(boletoResponse.desconto1))
        }

        if (this.temDesconto(boletoResponse.desconto2)) {
            descontos.add(parseDesconto(boletoResponse.desconto2))
        }

        if (this.temDesconto(boletoResponse.desconto3)) {
            descontos.add(parseDesconto(boletoResponse.desconto3))
        }

        return descontos.toSet()
    }

    private fun temDesconto(descontoResponse: RespostaDescontoInter): Boolean {
        return "NAOTEMDESCONTO" != descontoResponse.codigo
    }

    private fun parseDesconto(descontoResponse: RespostaDescontoInter): Desconto {
        return Desconto(
            tipo = parseTipoDesconto(descontoResponse.codigo),
            data = descontoResponse.data ?: LocalDate.now(),
            taxa = descontoResponse.taxa.toBigDecimal(),
            valor = descontoResponse.valor.toBigDecimal()
        )
    }

    private fun parseTipoDesconto(tipoDesconto: String): TipoDescontoEnum {
        return when (tipoDesconto) {
            "PERCENTUALDATAINFORMADA" -> TipoDescontoEnum.PERCENTUAL_DATA_INFORMADA
            "VALORFIXODATAINFORMADA" -> TipoDescontoEnum.VALOR_FIXO_DATA_INFORMADA
            "VALORANTECIPACAODIACORRIDO" -> TipoDescontoEnum.VALOR_ANTECIPACAO_DIA_CORRIDO
            "VALORANTECIPACAODIAUTIL" -> TipoDescontoEnum.VALOR_ANTECIPACAO_DIA_UTIL
            else -> {
                throw RuntimeException()
            }
        }
    }

    override fun recuperarPdfBoleto(boletoId: String): File {
        TODO("Not yet implemented")
    }

    override fun baixarBoleto(boletoId: String, motivoBaixa: String): Boleto {
        TODO("Not yet implemented")
    }
}
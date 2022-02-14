package online.getschool.cobranca.integracao.banco.inter.service

import online.getschool.cobranca.dominio.dto.boleto.*
import online.getschool.cobranca.integracao.banco.BoletoService
import online.getschool.cobranca.integracao.banco.inter.dto.resposta.RespostaBoletoInter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.util.MultiValueMap
import org.springframework.util.StringUtils
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class BoletoInterService : BoletoService {
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(BoletoInterService::class.java)
    }

    override fun emitir(boleto: Boleto): Boleto {
        TODO("Not yet implemented")
    }

    override fun recuperarBoletos(): Collection<Boleto> {
        TODO("Not yet implemented")
    }

    override fun recuperarBoleto(boletoId: String): Boleto {
        logger.debug("Recuperando boletos do banco inter")

        val headers = HttpHeaders()
        headers.add("x-inter-conta-corrente", "183769058")

        val httpEntity = HttpEntity<MultiValueMap<String, String>>(headers)

        val restTemplate = RestTemplateBuilder().build()

        val boletoResponse: RespostaBoletoInter = restTemplate.getForObject(
            "https://a6b8b4c8-036f-4a5e-9981-28ab9a0564d7.mock.pstmn.io/openbanking/v1/certificado/boletos/$boletoId",
            RespostaBoletoInter::class.java,
            httpEntity
        ) ?: throw IllegalArgumentException("Illegal state")

        val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        val descontos: MutableCollection<Desconto> = mutableSetOf()
        if ("NAOTEMDESCONTO" != boletoResponse.desconto1.codigo) {
            descontos.add(
                Desconto(
                    tipo = TipoDescontoEnum.PERCENTUAL_DATA_INFORMADA,
                    data = LocalDate.now(),
                    taxa = boletoResponse.desconto1.taxa.toBigDecimal(),
                    valor = boletoResponse.desconto1.valor.toBigDecimal()
                )
            )
        }

        if ("NAOTEMDESCONTO" != boletoResponse.desconto2.codigo) {
            descontos.add(
                Desconto(
                    tipo = TipoDescontoEnum.PERCENTUAL_DATA_INFORMADA,
                    data = LocalDate.now(),
                    taxa = boletoResponse.desconto2.taxa.toBigDecimal(),
                    valor = boletoResponse.desconto2.valor.toBigDecimal()
                )
            )
        }

        if ("NAOTEMDESCONTO" != boletoResponse.desconto3.codigo) {
            descontos.add(
                Desconto(
                    tipo = TipoDescontoEnum.PERCENTUAL_DATA_INFORMADA,
                    data = LocalDate.now(),
                    taxa = boletoResponse.desconto3.taxa.toBigDecimal(),
                    valor = boletoResponse.desconto3.valor.toBigDecimal()
                )
            )
        }

        val mensagens: MutableCollection<String> = mutableListOf()
        if (boletoResponse.mensagem.linha1.isNotBlank()) {
            mensagens.add(boletoResponse.mensagem.linha1)
        }
        if (boletoResponse.mensagem.linha2.isNotBlank()) {
            mensagens.add(boletoResponse.mensagem.linha2)
        }
        if (boletoResponse.mensagem.linha3.isNotBlank()) {
            mensagens.add(boletoResponse.mensagem.linha3)
        }
        if (boletoResponse.mensagem.linha4.isNotBlank()) {
            mensagens.add(boletoResponse.mensagem.linha4)
        }
        if (boletoResponse.mensagem.linha5.isNotBlank()) {
            mensagens.add(boletoResponse.mensagem.linha5)
        }

        return boletoResponse.let {
            Boleto(
                seuNumero = it.seuNumero.toInt(),
                valor = it.valorNominal.toBigDecimal(),
                dataEmissao = LocalDate.parse(it.dataEmissao, dateFormatter),
                dataVencimento = LocalDate.parse(it.dataVencimento, dateFormatter),
                nossoNumero = boletoId,
                codigoBarras = it.codigoBarras,
                linhaDigitavel = it.linhaDigitavel,
                status = StatusEnum.valueOf(it.situacao),
                motivoBaixa = StringUtils.capitalize(it.situacao),
                beneficiario = Beneficiario(
                    nome = it.nomeBeneficiario,
                    cnpj = it.cnpjCpfBeneficiario
                ),
                pagador = Pagador(
                    tipoPessoa = if ("FISICA" == it.tipoPessoaPagador) TipoPessoaEnum.PESSOA_FISICA else TipoPessoaEnum.PESSOA_JURIDICA,
                    nome = it.nomePagador,
                    documento = it.cnpjCpfPagador.toLong(),
                    email = it.emailPagador,
                    endereco = Endereco( //TODO: Consultar e preencher endereço
                        logradouro = "",
                        numero = 0,
                        bairro = "",
                        complemento = "",
                        cidade = "",
                        uf = "",
                        cep = ""
                    ),
                    telefone = Telefone(
                        ddd = it.dddPagador.toInt(),
                        numero = it.telefonePagador.toInt()
                    )
                ),
                descontos = descontos.toSet(),
                mora = Mora(
                    tipo = TipoMoraEnum.TAXA_MENSAL,
                    data = it.mora.data,
                    taxa = it.mora.taxa.toBigDecimal(),
                    valor = it.mora.valor.toBigDecimal()
                ),
                multa = Multa(
                    tipo = TipoMultaEnum.PERCENTUAL,
                    data = it.multa.data,
                    taxa = it.multa.taxa.toBigDecimal(),
                    valor = it.multa.valor.toBigDecimal()
                ),
                mensagens = mensagens.toSet()
            )
        }
    }

    override fun recuperarPdfBoleto(boletoId: String): File {
        TODO("Not yet implemented")
    }

    override fun baixarBoleto(boletoId: String, motivoBaixa: String): Boleto {
        TODO("Not yet implemented")
    }
}
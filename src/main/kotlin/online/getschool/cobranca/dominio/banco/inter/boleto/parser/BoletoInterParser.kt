package online.getschool.cobranca.dominio.banco.inter.boleto.parser

import online.getschool.cobranca.dominio.banco.inter.boleto.dto.resposta.detalhe.RespostaBoletoInter
import online.getschool.cobranca.dominio.banco.inter.boleto.dto.resposta.detalhe.RespostaMensagemInter
import online.getschool.cobranca.dominio.boleto.dto.*
import org.springframework.stereotype.Service

@Service
class BoletoInterParser(
    private val moraParser: MoraInterParser,
    private val multaParser: MultaInterParser,
    private val descontoParser: DescontosInterParser,
    private val statusParser: StatusInterParser
) {
    fun parseToBoleto(boletoInter: RespostaBoletoInter, nossoNumero: String): Boleto {
        return boletoInter.let {
            Boleto(
                seuNumero = it.seuNumero.toLong(),
                valor = it.valorNominal,
                dataEmissao = it.dataEmissao,
                dataVencimento = it.dataVencimento,
                nossoNumero = nossoNumero,
                codigoBarras = it.codigoBarras,
                linhaDigitavel = it.linhaDigitavel,
                status = statusParser.converterParaDominio(it),
                motivoBaixa = it.situacao,
                beneficiario = Beneficiario(
                    nome = it.nomeBeneficiario,
                    cnpj = it.cnpjCpfBeneficiario
                ),
                pagador = Pagador(
                    tipoPessoa = if ("FISICA" == it.tipoPessoaPagador) TipoPessoaEnum.PESSOA_FISICA else TipoPessoaEnum.PESSOA_JURIDICA,
                    nome = it.nomePagador,
                    documento = it.cnpjCpfPagador,
                    email = it.emailPagador,
                    endereco = Endereco(
                        logradouro = "",
                        numero = 0,
                        bairro = "",
                        cidade = "",
                        uf = "",
                        cep = ""
                    ),
                    telefone = Telefone(
                        ddd = it.dddPagador.toInt(),
                        numero = it.telefonePagador.toInt()
                    )
                ),
                descontos = descontoParser.converterParaDominio(it),
                mora = moraParser.converterParaDominio(it.mora),
                multa = multaParser.converterParaDominio(it.multa),
                mensagens = parseMensagens(it.mensagem)
            )
        }
    }

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
}
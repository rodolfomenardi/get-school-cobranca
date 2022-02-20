package online.getschool.cobranca.dominio.banco.inter.boleto.service

import online.getschool.cobranca.dominio.banco.inter.boleto.adapter.ApiBoletoInterAdapter
import online.getschool.cobranca.dominio.banco.inter.boleto.parser.*
import online.getschool.cobranca.dominio.boleto.dto.*
import online.getschool.cobranca.dominio.boleto.service.BoletoService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.File

@Service
class BoletoInterService(
    private val apiAdapter: ApiBoletoInterAdapter,
    private val boletoParser: BoletoInterParser,
    private val multaParser: MultaInterParser,
    private val moraParser: MoraInterParser,
    private val descontosParser: DescontosInterParser,
    private val statusParser: StatusInterParser
) : BoletoService {
    companion object {
        private val logger = LoggerFactory.getLogger(BoletoInterService::class.java)
    }

    override fun emitir(boleto: Boleto): Boleto {
        TODO("Not yet implemented")
    }

    override fun recuperarBoletos(): Collection<Boleto> {
        logger.debug("Recuperando informações dos boletos do Banco Inter")

        val parametros = mapOf(
            "filtrarPor" to "TODOS",
            "filtrarDataPor" to "VENCIMENTO",
            "dataInicial" to "2022-01-01",
            "dataFinal" to "2022-02-28"
        )
        return apiAdapter.recuperarBoletos(parametros).let { response ->
            response.boletos.map {
                Boleto(
                    seuNumero = it.seuNumero.toLong(),
                    valor = it.valorNominal,
                    dataEmissao = it.dataEmissao,
                    dataVencimento = it.dataVencimento,
                    pagador = Pagador(
                        tipoPessoa = TipoPessoaEnum.PESSOA_FISICA,
                        nome = it.nomePagador,
                        documento = it.cnpjCpfPagador,
                        email = it.emailPagador,
                        endereco = Endereco(
                            logradouro = "",
                            numero = 0,
                            bairro = "",
                            complemento = "",
                            cidade = "",
                            uf = "",
                            cep = ""
                        ),
                        telefone = Telefone(
                            ddd = 17, //TODO it.telefone.toInt()
                            numero = 36216444 // TODO it.telefone.toInt()
                        )
                    ),
                    beneficiario = Beneficiario(
                        nome = "", // TODO
                        cnpj = "", //TODO
                    ),
                    mensagens = emptyList(), //TODO
                    descontos = descontosParser.converterParaDominio(it),
                    multa = multaParser.converterParaDominio(it.multa),
                    mora = moraParser.converterParaDominio(it.mora),
                    nossoNumero = it.nossoNumero,
                    codigoBarras = "", //TODO
                    linhaDigitavel = it.linhaDigitavel,
                    status = statusParser.converterParaDominio(it),
                    motivoBaixa = it.situacao
                )
            }
        }
    }

    override fun recuperarBoleto(nossoNumero: String): Boleto {
        logger.debug("Recuperando informações do boleto do Banco Inter com nosso número = $nossoNumero")

        val boletoResponse = apiAdapter.recuperarBoleto(nossoNumero)
        return boletoParser.parseToBoleto(
            boletoInter = boletoResponse,
            nossoNumero = nossoNumero
        )
    }

    override fun recuperarPdfBoleto(nossoNumero: String): File {
        logger.debug("Recuperando PDF do boleto do Banco Inter com nosso número = $nossoNumero")

        return apiAdapter.recuperarPdfBoleto(nossoNumero = nossoNumero)
    }

    override fun baixarBoleto(nossoNumero: String, motivoBaixa: String): Boleto {
        TODO("Not yet implemented")
    }
}
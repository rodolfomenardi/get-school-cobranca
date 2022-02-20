package online.getschool.cobranca.dominio.banco.inter.boleto.adapter

import online.getschool.cobranca.dominio.banco.inter.boleto.dto.resposta.detalhe.RespostaBoletoInter
import online.getschool.cobranca.dominio.banco.inter.boleto.dto.resposta.lista.Boletos
import online.getschool.cobranca.dominio.shared.servico.RestClientService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.web.util.UriComponentsBuilder
import java.io.File
import java.util.*


@Service
class ApiRestBoletoInterAdapter(
    @Value("\${banco.inter.url.rest}") private val urlBase: String,
    @Value("\${banco.inter.numero.conta}") private val numeroConta: String,
    private val restClientService: RestClientService
) : ApiBoletoInterAdapter {
    override fun recuperarBoletos(parameters: Map<String, String>): Boletos {
        val httpEntity = this.recuperarHttpEntity()

        val urlTemplate = UriComponentsBuilder.fromHttpUrl("$urlBase/boletos/")
            .queryParam("filtrarPor", "{filtrarPor}")
            .queryParam("filtrarDataPor", "{filtrarDataPor}")
            .queryParam("dataInicial", "{dataInicial}")
            .queryParam("dataFinal", "{dataFinal}")
            .encode()
            .toUriString()

        return restClientService.get(
            urlTemplate,
            Boletos::class.java,
            httpEntity,
            parameters
        ).body ?: throw IllegalArgumentException("Boletos não encontrado no Banco Inter")
    }

    override fun recuperarBoleto(nossoNumero: String): RespostaBoletoInter {
        val httpEntity = this.recuperarHttpEntity()

        return restClientService.get(
            "$urlBase/boletos/$nossoNumero",
            RespostaBoletoInter::class.java,
            httpEntity,
            emptyMap()
        ).body ?: throw IllegalArgumentException("Boleto não encontrado no Banco Inter")
    }

    override fun recuperarPdfBoleto(nossoNumero: String): File {
        val httpEntity = this.recuperarHttpEntity()

        val base64: String = restClientService.get(
            "$urlBase/boletos/$nossoNumero/pdf",
            String::class.java,
            httpEntity,
            emptyMap()
        ).body ?: throw IllegalArgumentException("Boleto não encontrado no Banco Inter")

        val pdf: ByteArray = Base64.getDecoder().decode(base64)
        val file = File("/tmp/$nossoNumero.pdf")
        file.writeBytes(pdf)
        return file
    }


    private fun recuperarHttpEntity(): HttpEntity<Any> {
        val headers = recuperarHeaders()

        return HttpEntity<Any>(headers)
    }

    private fun recuperarHeaders(): HttpHeaders {
        val headers = HttpHeaders()
        headers.add("x-inter-conta-corrente", this.numeroConta)
        return headers
    }
}
package online.getschool.cobranca.integracao.banco.inter.adapter

import online.getschool.cobranca.integracao.banco.inter.dto.resposta.RespostaBoletoInter
import online.getschool.cobranca.services.RestClientService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service

@Service
class ApiRestBoletoInterAdapter(
    @Value("\${banco.inter.url.rest}") private val urlBase: String,
    @Value("\${banco.inter.numero.conta}") private val numeroConta: String,
    private val restClientService: RestClientService
) : ApiBoletoInterAdapter {

    override fun recuperarBoleto(boletoId: String): RespostaBoletoInter {
        val httpEntity = this.recuperarHttpEntity()

        return restClientService.get(
            "${urlBase}/boletos/$boletoId",
            RespostaBoletoInter::class.java,
            httpEntity,
            emptyMap()
        ).body ?: throw IllegalArgumentException("Boleto não encontrado no Banco Inter")
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
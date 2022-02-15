package online.getschool.cobranca.services

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class RestClientServiceImpRestTemplate<T> : RestClientService {
    companion object {
        private val restTemplate = RestTemplateBuilder().build()
    }

    override fun post(url: String, body: Any, headers: Any) {
        TODO("Not yet implemented")
    }

    override fun <T> get(url: String, responseType: Class<T>, httpEntity: HttpEntity<Any>, parameters: Map<String, Any>): ResponseEntity<T> {
        return restTemplate.exchange(
            url,
            HttpMethod.GET,
            httpEntity,
            responseType,
            parameters
        )
    }
}
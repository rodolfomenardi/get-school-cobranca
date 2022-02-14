package online.getschool.cobranca.services

import org.springframework.http.HttpEntity

interface RestClientService {
    fun post(url: String, body: Any, headers: Any)
    fun <T> get(url: String, responseType: Class<T>, httpEntity: HttpEntity<Any>, parameters: Map<String, Any>): T
}
package online.getschool.cobranca.dominio.shared.servico

import org.springframework.http.HttpEntity
import org.springframework.http.ResponseEntity

interface RestClientService {
    fun post(url: String, body: Any, headers: Any)
    fun <T> get(url: String, responseType: Class<T>, httpEntity: HttpEntity<Any>, parameters: Map<String, Any>): ResponseEntity<T>
}
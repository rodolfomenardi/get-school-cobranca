package online.getschool.cobranca.dominio.boleto.dto

data class Endereco(
    val logradouro: String,
    val numero: Int,
    val bairro: String,
    val complemento: String = "",
    val cidade: String,
    val uf: String,
    val cep: String
)

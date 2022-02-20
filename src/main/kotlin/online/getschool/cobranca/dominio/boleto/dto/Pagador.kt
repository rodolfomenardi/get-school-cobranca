package online.getschool.cobranca.dominio.boleto.dto

data class Pagador(
    val tipoPessoa: TipoPessoaEnum,
    val nome: String,
    val documento: String,
    val email: String,
    val endereco: Endereco,
    val telefone: Telefone
)
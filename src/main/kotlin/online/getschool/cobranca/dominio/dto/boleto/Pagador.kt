package online.getschool.cobranca.dominio.dto.boleto

data class Pagador(
    val tipoPessoa: TipoPessoaEnum,
    val nome: String,
    val documento: Long,
    val email: String,
    val endereco: Endereco,
    val telefone: Telefone
)
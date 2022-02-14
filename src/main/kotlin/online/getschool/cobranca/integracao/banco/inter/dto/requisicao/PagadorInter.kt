package online.getschool.cobranca.integracao.banco.inter.dto

data class PagadorInter(
    val tipoPessoa: TipoPessoaInterEnum,
    val nome: String,
    val endereco: String,
    val numero: String,
    val complemento: String,
    val bairro: String,
    val cidade: String,
    val uf: String,
    val cep: String,
    val cnpjCpf: String,
    val email: String,
    val ddd: String,
    val telefone: String
)

package online.getschool.cobranca.dominio.banco.inter.boleto.dto.shared

data class PagadorInter(
    val tipoPessoaPagador: String,
    val nomePagador: String,
    val emailPagador: String,
    val cnpjCpfPagador: String,
    val dddPagador: String,
    val telefonePagador: String,
)

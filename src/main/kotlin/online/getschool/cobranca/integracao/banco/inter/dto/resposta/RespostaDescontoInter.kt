package online.getschool.cobranca.integracao.banco.inter.dto.resposta

import java.time.LocalDate

data class RespostaDescontoInter(
    val codigo: String,
    val data: LocalDate? = null,
    val taxa: Float,
    val valor: Float
)

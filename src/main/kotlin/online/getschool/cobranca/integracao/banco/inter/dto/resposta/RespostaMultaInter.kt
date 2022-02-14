package online.getschool.cobranca.integracao.banco.inter.dto.resposta

import java.time.LocalDate

data class RespostaMultaInter(
    val codigo: String,
    val data: LocalDate,
    val taxa: Float,
    val valor: Float
)

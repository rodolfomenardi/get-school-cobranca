package online.getschool.cobranca.integracao.banco.inter.dto

import java.time.LocalDate

data class MoraInter(
    val codigoMora: TipoMoraInterEnum,
    val data: LocalDate,
    val taxa: Float,
    val valor: Float
)

package online.getschool.cobranca.integracao.banco.inter.dto

import java.time.LocalDate

data class MultaInter(
    val codigoMulta: TipoMultaInterEnum,
    val data: LocalDate,
    val taxa: Float,
    val valor: Float
)

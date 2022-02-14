package online.getschool.cobranca.integracao.banco.inter.dto

import java.time.LocalDate

data class DescontoInter(
    val codigoDesconto: TipoDescontoInterEnum,
    val data: LocalDate,
    val taxa: Float,
    val valor: Float
)

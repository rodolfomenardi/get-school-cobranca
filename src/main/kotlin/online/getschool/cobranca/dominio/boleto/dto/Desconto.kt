package online.getschool.cobranca.dominio.boleto.dto

import java.math.BigDecimal
import java.time.LocalDate

data class Desconto(
    val tipo: TipoDescontoEnum,
    val data: LocalDate?,
    val taxa: BigDecimal,
    val valor: BigDecimal
)

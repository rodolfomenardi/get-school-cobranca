package online.getschool.cobranca.dominio.banco.inter.boleto.dto.shared

import java.math.BigDecimal
import java.time.LocalDate

data class MultaInter(
    val codigo: TipoMultaInterEnum,
    val data: LocalDate,
    val taxa: BigDecimal,
    val valor: BigDecimal
)

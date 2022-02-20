package online.getschool.cobranca.dominio.banco.inter.boleto.dto.shared

import com.fasterxml.jackson.annotation.JsonFormat
import java.math.BigDecimal
import java.time.LocalDate

data class DescontoInter(
    val codigo: TipoDescontoInterEnum,
    @JsonFormat(pattern = "dd/MM/yyyy")
    val data: LocalDate?,
    val taxa: BigDecimal,
    val valor: BigDecimal
)

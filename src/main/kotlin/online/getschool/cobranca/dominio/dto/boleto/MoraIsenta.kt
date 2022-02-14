package online.getschool.cobranca.dominio.dto.boleto

import java.math.BigDecimal
import java.time.LocalDate

class MoraIsenta :
    Mora(tipo = TipoMoraEnum.ISENTO, data = LocalDate.now(), taxa = BigDecimal.ZERO, valor = BigDecimal.ZERO)
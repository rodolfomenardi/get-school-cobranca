package online.getschool.cobranca.dominio.dto.boleto

import java.math.BigDecimal
import java.time.LocalDate

class MultaIsenta :
    Multa(tipo = TipoMultaEnum.ISENTO, data = LocalDate.now(), taxa = BigDecimal.ZERO, valor = BigDecimal.ZERO)
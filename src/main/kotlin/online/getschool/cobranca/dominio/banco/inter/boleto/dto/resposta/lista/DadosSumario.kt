package online.getschool.cobranca.dominio.banco.inter.boleto.dto.resposta.lista

import java.math.BigDecimal

data class DadosSumario(
    val quantidade: Int,
    val valor: BigDecimal
)

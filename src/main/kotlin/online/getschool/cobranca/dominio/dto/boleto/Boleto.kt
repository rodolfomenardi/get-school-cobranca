package online.getschool.cobranca.dominio.dto.boleto

import java.math.BigDecimal
import java.time.LocalDate

data class Boleto(
    val seuNumero: Int,
    val valor: BigDecimal,
    val dataEmissao: LocalDate,
    val dataVencimento: LocalDate,
    val pagador: Pagador,
    val beneficiario: Beneficiario,
    val mensagens: Collection<String> = emptyList(),
    val descontos: Collection<Desconto> = emptySet(),
    val multa: Multa = MultaIsenta(),
    val mora: Mora = MoraIsenta(),
    val nossoNumero: String = "",
    val codigoBarras: String = "",
    val linhaDigitavel: String = "",
    val status: StatusEnum = StatusEnum.PENDENTE,
    val motivoBaixa: String = ""
)
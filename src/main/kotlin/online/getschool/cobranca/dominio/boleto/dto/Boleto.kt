package online.getschool.cobranca.dominio.boleto.dto

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.LocalDate

@Document(collection = "boletos")
data class Boleto(
    @Id val seuNumero: Long,
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
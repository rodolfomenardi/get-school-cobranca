package online.getschool.cobranca.dominio.banco.inter.boleto.dto.shared

import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

abstract class BoletoInterCommon(
    open val dataHoraSituacao: LocalDateTime,
    open val linhaDigitavel: String,
    open val dataVencimento: LocalDate,
    open val dataEmissao: LocalDate,
    open val seuNumero: String,
    open val valorNominal: BigDecimal,
    open val nomePagador: String,
    open val emailPagador: String,
    open val cnpjCpfPagador: String,
    open val dataLimitePagamento: LocalDate,
    open val valorAbatimento: BigDecimal,
    open val situacao: String,
    open val desconto1: DescontoInter,
    open val desconto2: DescontoInter,
    open val desconto3: DescontoInter,
    open val multa: MultaInter,
    open val mora: MoraInter,
)
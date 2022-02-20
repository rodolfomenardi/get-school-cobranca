package online.getschool.cobranca.dominio.banco.inter.boleto.dto.resposta.detalhe

import com.fasterxml.jackson.annotation.JsonFormat
import online.getschool.cobranca.dominio.banco.inter.boleto.dto.shared.BoletoInterCommon
import online.getschool.cobranca.dominio.banco.inter.boleto.dto.shared.DescontoInter
import online.getschool.cobranca.dominio.banco.inter.boleto.dto.shared.MoraInter
import online.getschool.cobranca.dominio.banco.inter.boleto.dto.shared.MultaInter
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

data class RespostaBoletoInter(
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    override val dataHoraSituacao: LocalDateTime,
    override val linhaDigitavel: String,
    @JsonFormat(pattern = "dd/MM/yyyy")
    override val dataVencimento: LocalDate,
    @JsonFormat(pattern = "dd/MM/yyyy")
    override val dataEmissao: LocalDate,
    override val seuNumero: String,
    override val valorNominal: BigDecimal,
    override val nomePagador: String,
    override val emailPagador: String,
    override val cnpjCpfPagador: String,
    @JsonFormat(pattern = "dd/MM/yyyy")
    override val dataLimitePagamento: LocalDate,
    override val valorAbatimento: BigDecimal,
    override val situacao: String,
    override val desconto1: DescontoInter,
    override val desconto2: DescontoInter,
    override val desconto3: DescontoInter,
    override val multa: MultaInter,
    override val mora: MoraInter,
    val nomeBeneficiario: String,
    val cnpjCpfBeneficiario: String,
    val tipoPessoaBeneficiario: String,
    val codigoBarras: String,
    val dddPagador: String,
    val telefonePagador: String,
    val tipoPessoaPagador: String,
    val codigoEspecie: String,
    val situacaoPagamento: String,
    val mensagem: RespostaMensagemInter
) : BoletoInterCommon(
    dataHoraSituacao = dataHoraSituacao,
    linhaDigitavel = linhaDigitavel,
    dataVencimento = dataVencimento,
    dataEmissao = dataEmissao,
    seuNumero = seuNumero,
    valorNominal = valorNominal,
    nomePagador = nomePagador,
    emailPagador = emailPagador,
    cnpjCpfPagador = cnpjCpfPagador,
    dataLimitePagamento = dataLimitePagamento,
    valorAbatimento = valorAbatimento,
    situacao = situacao,
    desconto1 = desconto1,
    desconto2 = desconto2,
    desconto3 = desconto3,
    multa = multa,
    mora = mora
)

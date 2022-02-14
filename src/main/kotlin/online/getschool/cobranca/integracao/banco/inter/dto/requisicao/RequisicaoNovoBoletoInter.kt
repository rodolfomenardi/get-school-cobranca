package online.getschool.cobranca.integracao.banco.inter.dto

import java.time.LocalDate

data class RequisicaoNovoBoletoInter(
    val seuNumero: String,
    val cnpjCPFBeneficiario: String,
    val valorNominal: Float,
    val valorAbatimento: Float,
    val dataEmissao: LocalDate,
    val dataVencimento: LocalDate,
    val numDiasAgenda: NumeroDiasBaixaAutomaticaInterEnum,
    val pagador: PagadorInter,
    val mensagem: MensagemInter,
    val desconto1: DescontoInter,
    val desconto2: DescontoInter,
    val desconto3: DescontoInter,
    val multa: MultaInter,
    val mora: MoraInter
)

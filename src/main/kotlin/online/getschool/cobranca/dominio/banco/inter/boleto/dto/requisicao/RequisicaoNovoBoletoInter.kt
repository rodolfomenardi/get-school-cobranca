package online.getschool.cobranca.dominio.banco.inter.boleto.dto.requisicao

import online.getschool.cobranca.dominio.banco.inter.boleto.dto.shared.DescontoInter
import online.getschool.cobranca.dominio.banco.inter.boleto.dto.shared.MoraInter
import online.getschool.cobranca.dominio.banco.inter.boleto.dto.shared.MultaInter

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

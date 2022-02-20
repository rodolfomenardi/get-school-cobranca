package online.getschool.cobranca.dominio.banco.inter.boleto.parser

import online.getschool.cobranca.dominio.banco.inter.boleto.dto.shared.BoletoInterCommon
import online.getschool.cobranca.dominio.boleto.dto.StatusEnum
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class StatusInterParser : InterParser<BoletoInterCommon, StatusEnum> {
    override fun converterParaDominio(dto: BoletoInterCommon): StatusEnum {
        return if (estaPago(dto)) {
            StatusEnum.PAGO
        } else if (estaVencido(dto)) {
            StatusEnum.VENCIDO
        } else if (estaEmAberto(dto)) {
            StatusEnum.EMITIDO
        } else if (estaCancelado(dto)) {
            StatusEnum.CANCELADO
        } else {
            StatusEnum.PENDENTE
        }
    }

    private fun estaPago(boletoInter: BoletoInterCommon) = "PAGO" == boletoInter.situacao

    private fun estaVencido(dto: BoletoInterCommon) =
        estaEmAberto(dto) && (dto.dataVencimento.isAfter(LocalDate.now()))

    private fun estaEmAberto(dto: BoletoInterCommon) =
        "EMABERTO" == dto.situacao

    private fun estaCancelado(dto: BoletoInterCommon) = "CANCELADO" == dto.situacao
}
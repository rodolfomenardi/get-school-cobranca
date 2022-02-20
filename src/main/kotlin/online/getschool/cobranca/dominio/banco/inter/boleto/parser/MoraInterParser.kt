package online.getschool.cobranca.dominio.banco.inter.boleto.parser


import online.getschool.cobranca.dominio.banco.inter.boleto.dto.shared.MoraInter
import online.getschool.cobranca.dominio.banco.inter.boleto.dto.shared.TipoMoraInterEnum
import online.getschool.cobranca.dominio.boleto.dto.Mora
import online.getschool.cobranca.dominio.boleto.dto.TipoMoraEnum
import org.springframework.stereotype.Service

@Service
class MoraInterParser : InterParser<MoraInter, Mora> {
    override fun converterParaDominio(dto: MoraInter) = Mora(
        tipo = parseTipo(dto.codigo),
        data = dto.data,
        taxa = dto.taxa,
        valor = dto.valor
    )

    private fun parseTipo(codigo: TipoMoraInterEnum) = TipoMoraEnum.valueOf(codigo.name)
}
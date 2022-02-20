package online.getschool.cobranca.dominio.banco.inter.boleto.parser

import online.getschool.cobranca.dominio.banco.inter.boleto.dto.shared.MultaInter
import online.getschool.cobranca.dominio.banco.inter.boleto.dto.shared.TipoMultaInterEnum
import online.getschool.cobranca.dominio.boleto.dto.Multa
import online.getschool.cobranca.dominio.boleto.dto.TipoMultaEnum
import org.springframework.stereotype.Service

@Service
class MultaInterParser : InterParser<MultaInter, Multa> {
    override fun converterParaDominio(dto: MultaInter) = Multa(
        tipo = parseTipo(dto.codigo),
        data = dto.data,
        taxa = dto.taxa,
        valor = dto.valor
    )

    private fun parseTipo(codigo: TipoMultaInterEnum) = TipoMultaEnum.valueOf(codigo.name)
}
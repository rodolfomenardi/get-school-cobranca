package online.getschool.cobranca.dominio.banco.inter.boleto.parser

import online.getschool.cobranca.dominio.banco.inter.boleto.dto.shared.BoletoInterCommon
import online.getschool.cobranca.dominio.banco.inter.boleto.dto.shared.DescontoInter
import online.getschool.cobranca.dominio.banco.inter.boleto.dto.shared.TipoDescontoInterEnum
import online.getschool.cobranca.dominio.boleto.dto.Desconto
import online.getschool.cobranca.dominio.boleto.dto.TipoDescontoEnum
import org.springframework.stereotype.Service

@Service
class DescontosInterParser : InterParser<BoletoInterCommon, Collection<Desconto>> {
    override fun converterParaDominio(dto: BoletoInterCommon): Collection<Desconto> {
        val descontos = mutableSetOf<Desconto>()

        if (temDesconto(dto.desconto1)) {
            descontos.add(parseDesconto(dto.desconto1))
        }

        if (temDesconto(dto.desconto2)) {
            descontos.add(parseDesconto(dto.desconto2))
        }

        if (temDesconto(dto.desconto3)) {
            descontos.add(parseDesconto(dto.desconto3))
        }

        return descontos.toSet()
    }

    private fun temDesconto(descontoResponse: DescontoInter) =
        TipoDescontoInterEnum.NAO_TEM_DESCONTO != descontoResponse.codigo

    private fun parseDesconto(descontoResponse: DescontoInter) = Desconto(
        tipo = parseTipo(descontoResponse.codigo),
        data = descontoResponse.data,
        taxa = descontoResponse.taxa,
        valor = descontoResponse.valor
    )

    private fun parseTipo(tipo: TipoDescontoInterEnum) = TipoDescontoEnum.valueOf(tipo.name)
}
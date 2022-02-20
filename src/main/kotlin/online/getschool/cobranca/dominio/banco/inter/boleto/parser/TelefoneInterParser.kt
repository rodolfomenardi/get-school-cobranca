package online.getschool.cobranca.dominio.banco.inter.boleto.parser

import online.getschool.cobranca.dominio.banco.inter.boleto.dto.requisicao.PagadorInter
import online.getschool.cobranca.dominio.boleto.dto.Telefone
import org.springframework.stereotype.Service

@Service
class TelefoneInterParser : InterParser<PagadorInter, Telefone> {
    override fun converterParaDominio(dto: PagadorInter) = Telefone(
        ddd = dto.ddd.toInt(),
        numero = dto.numero.toInt()
    )
}

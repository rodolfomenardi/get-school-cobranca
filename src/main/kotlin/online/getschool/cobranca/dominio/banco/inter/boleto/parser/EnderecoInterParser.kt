package online.getschool.cobranca.dominio.banco.inter.boleto.parser

import online.getschool.cobranca.dominio.banco.inter.boleto.dto.requisicao.PagadorInter
import online.getschool.cobranca.dominio.boleto.dto.Endereco
import org.springframework.stereotype.Service

@Service
class EnderecoInterParser : InterParser<PagadorInter, Endereco> {
    override fun converterParaDominio(dto: PagadorInter) = Endereco(
        logradouro = dto.endereco,
        numero = dto.numero.toInt(),
        bairro = dto.bairro,
        complemento = dto.complemento,
        cidade = dto.cidade,
        uf = dto.uf,
        cep = dto.cep
    )
}

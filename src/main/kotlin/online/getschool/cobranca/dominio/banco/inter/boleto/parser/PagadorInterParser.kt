package online.getschool.cobranca.dominio.banco.inter.boleto.parser

import online.getschool.cobranca.dominio.banco.inter.boleto.dto.requisicao.PagadorInter
import online.getschool.cobranca.dominio.banco.inter.boleto.dto.requisicao.TipoPessoaInterEnum
import online.getschool.cobranca.dominio.boleto.dto.Pagador
import online.getschool.cobranca.dominio.boleto.dto.TipoPessoaEnum
import org.springframework.stereotype.Service

@Service
class PagadorInterParser(
    private val enderecoParser: EnderecoInterParser,
    private val telefoneParser: TelefoneInterParser
) : InterParser<PagadorInter, Pagador> {
    override fun converterParaDominio(dto: PagadorInter) = Pagador(
        tipoPessoa = parseTipo(dto.tipoPessoa),
        nome = dto.nome,
        documento = dto.cnpjCpf,
        email = dto.email,
        endereco = enderecoParser.converterParaDominio(dto),
        telefone = telefoneParser.converterParaDominio(dto)
    )

    private fun parseTipo(tipoPessoa: TipoPessoaInterEnum) = TipoPessoaEnum.valueOf(tipoPessoa.name)
}
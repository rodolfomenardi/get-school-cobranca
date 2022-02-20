package online.getschool.cobranca.dominio.banco.inter.boleto.parser

interface InterParser<E, S> {
    fun converterParaDominio(dto: E): S
}
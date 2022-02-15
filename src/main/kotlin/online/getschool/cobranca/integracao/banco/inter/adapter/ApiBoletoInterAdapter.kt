package online.getschool.cobranca.integracao.banco.inter.adapter

import online.getschool.cobranca.integracao.banco.inter.dto.resposta.RespostaBoletoInter

interface ApiBoletoInterAdapter {
    fun recuperarBoleto(boletoId: String): RespostaBoletoInter
}
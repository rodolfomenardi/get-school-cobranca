package online.getschool.cobranca.dominio.banco.inter.boleto.adapter

import online.getschool.cobranca.dominio.banco.inter.boleto.dto.resposta.detalhe.RespostaBoletoInter
import online.getschool.cobranca.dominio.banco.inter.boleto.dto.resposta.lista.Boletos
import java.io.File

interface ApiBoletoInterAdapter {
    fun recuperarBoletos(parameters: Map<String, String>): Boletos
    fun recuperarBoleto(nossoNumero: String): RespostaBoletoInter
    fun recuperarPdfBoleto(nossoNumero: String): File
}
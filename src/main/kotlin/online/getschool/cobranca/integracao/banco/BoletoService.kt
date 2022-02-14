package online.getschool.cobranca.integracao.banco

import online.getschool.cobranca.dominio.dto.boleto.Boleto
import java.io.File

interface BoletoService {
    fun emitir(boleto: Boleto): Boleto
    fun recuperarBoletos(): Collection<Boleto>
    fun recuperarBoleto(boletoId: String): Boleto
    fun recuperarPdfBoleto(boletoId: String): File
    fun baixarBoleto(boletoId: String, motivoBaixa: String): Boleto
}
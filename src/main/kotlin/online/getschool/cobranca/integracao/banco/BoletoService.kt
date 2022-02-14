package online.getschool.cobranca.integracao.banco

import online.getschool.cobranca.dominio.dto.boleto.Boleto
import org.springframework.stereotype.Service
import java.io.File

@Service
interface BoletoService {
    fun emitir(boleto: Boleto): Boleto
    fun recuperarBoletos(): Collection<Boleto>
    fun recuperarBoleto(boletoId: String): Boleto
    fun recuperarPdfBoleto(boletoId: String): File
    fun baixarBoleto(boletoId: String, motivoBaixa: String): Boleto
}
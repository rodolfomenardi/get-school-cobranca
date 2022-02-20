package online.getschool.cobranca.dominio.boleto.service

import online.getschool.cobranca.dominio.boleto.dto.Boleto
import org.springframework.stereotype.Service
import java.io.File

@Service
interface BoletoService {
    fun emitir(boleto: Boleto): Boleto
    fun recuperarBoletos(): Collection<Boleto>
    fun recuperarBoleto(nossoNumero: String): Boleto
    fun recuperarPdfBoleto(nossoNumero: String): File
    fun baixarBoleto(nossoNumero: String, motivoBaixa: String): Boleto
}
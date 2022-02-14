package online.getschool.cobranca.integracao.banco.inter.service

import online.getschool.cobranca.integracao.banco.BoletoService
import org.junit.jupiter.api.Test

internal class BoletoInterServiceTest {
    @Test
    fun deveRecuperarBoletoDadoIdValido() {
        val boletoId = "00779818998"

        val boletoService: BoletoService = BoletoInterService()
        val boleto = boletoService.recuperarBoleto(boletoId)

        println(boleto)
    }
}
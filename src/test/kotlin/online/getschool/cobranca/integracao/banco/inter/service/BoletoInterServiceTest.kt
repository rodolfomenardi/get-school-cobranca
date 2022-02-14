package online.getschool.cobranca.integracao.banco.inter.service

import online.getschool.cobranca.integracao.banco.BoletoService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class BoletoInterServiceTest {
    lateinit var boletoService: BoletoService
    @Autowired
    fun BoletoInterServiceTest(boletoService: BoletoService) {
        this.boletoService = boletoService
    }
    @Test
    fun deveRecuperarBoletoDadoIdValido() {
        val boletoId = "00779818998"

        val boleto = this.boletoService.recuperarBoleto(boletoId)

        println(boleto)
    }
}
package online.getschool.cobranca.integracao.banco.inter.service

import online.getschool.cobranca.dominio.boleto.repository.BoletoRepository
import online.getschool.cobranca.dominio.boleto.service.BoletoService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class BoletoInterServiceTest(
    @Autowired private val boletoService: BoletoService,
    @Autowired private val boletoRepository: BoletoRepository
) {

    @Test
    fun deveRecuperarTodosBoletos() {
        println(boletoService.recuperarBoletos())
    }

    @Test
    fun deveRecuperarBoletoDadoIdValido() {
        val boletoId = "00779818998"

        val boleto = this.boletoService.recuperarBoleto(nossoNumero = boletoId)

        println(boletoRepository.save(boleto))
    }

    @Test
    fun deveRecuperarPdfBoletoIdValido() {
        val boletoId = "00779818998"
        val pdf = this.boletoService.recuperarPdfBoleto(nossoNumero = boletoId)

        println(pdf)
    }
}
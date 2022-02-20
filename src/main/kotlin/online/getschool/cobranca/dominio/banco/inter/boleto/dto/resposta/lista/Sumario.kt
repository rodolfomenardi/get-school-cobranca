package online.getschool.cobranca.dominio.banco.inter.boleto.dto.resposta.lista

data class Sumario(
    val recebidos: DadosSumario,
    val previstos: DadosSumario,
    val baixados: DadosSumario,
    val expirados: DadosSumario
)

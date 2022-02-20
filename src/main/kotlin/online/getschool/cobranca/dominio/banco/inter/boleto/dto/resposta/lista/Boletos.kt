package online.getschool.cobranca.dominio.banco.inter.boleto.dto.resposta.lista

import com.fasterxml.jackson.annotation.JsonAlias

data class Boletos(
    @JsonAlias("totalPages")
    val totalDePaginas: Int,
    @JsonAlias("totalElements")
    val totalDeElementos: Int,
    @JsonAlias("numberOfElements")
    val numeroDeElementos: Int,
    @JsonAlias("last")
    val ultimoItem: Boolean,
    @JsonAlias("first")
    val primeiroItem: Boolean,
    @JsonAlias("size")
    val tamanho: Int,
    @JsonAlias("summary")
    val sumario: Sumario,
    @JsonAlias("content")
    val boletos: Collection<Boleto>
    )

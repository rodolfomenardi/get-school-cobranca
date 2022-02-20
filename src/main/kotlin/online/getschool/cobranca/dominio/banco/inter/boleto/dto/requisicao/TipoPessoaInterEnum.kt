package online.getschool.cobranca.dominio.banco.inter.boleto.dto.requisicao

import com.fasterxml.jackson.annotation.JsonAlias

enum class TipoPessoaInterEnum {
    @JsonAlias("FISICA")
    PESSOA_FISICA,
    @JsonAlias("JURIDICA")
    PESSOA_JURIDICA
}
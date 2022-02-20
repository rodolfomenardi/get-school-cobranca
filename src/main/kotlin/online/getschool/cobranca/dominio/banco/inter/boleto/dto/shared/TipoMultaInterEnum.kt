package online.getschool.cobranca.dominio.banco.inter.boleto.dto.shared

import com.fasterxml.jackson.annotation.JsonAlias

enum class TipoMultaInterEnum {
    @JsonAlias("NAOTEMMULTA")
    ISENTO,
    @JsonAlias("VALORFIXO")
    VALOR_FIXO,
    PERCENTUAL
}
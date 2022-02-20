package online.getschool.cobranca.dominio.banco.inter.boleto.dto.shared

import com.fasterxml.jackson.annotation.JsonAlias

enum class TipoMoraInterEnum {
    @JsonAlias("VALORDIA")
    VALOR_DIA,
    @JsonAlias("TAXAMENSAL")
    TAXA_MENSAL,
    ISENTO
}
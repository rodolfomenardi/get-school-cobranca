package online.getschool.cobranca.dominio.dto.boleto

import java.math.BigDecimal
import java.time.LocalDate

open class Multa(
    val tipo: TipoMultaEnum,
    val data: LocalDate,
    val taxa: BigDecimal,
    val valor: BigDecimal
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Multa) return false

        if (tipo != other.tipo) return false
        if (data != other.data) return false
        if (taxa != other.taxa) return false
        if (valor != other.valor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = tipo.hashCode()
        result = 31 * result + data.hashCode()
        result = 31 * result + taxa.hashCode()
        result = 31 * result + valor.hashCode()
        return result
    }

    override fun toString(): String {
        return "Multa(tipo=$tipo, data=$data, taxa=$taxa, valor=$valor)"
    }
}

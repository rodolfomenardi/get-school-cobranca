package online.getschool.cobranca.dominio.boleto.repository

import online.getschool.cobranca.dominio.boleto.dto.Boleto
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface BoletoRepository : MongoRepository<Boleto, Long>
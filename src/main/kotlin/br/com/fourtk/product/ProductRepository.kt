package br.com.fourtk.product

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface ProductRepository : JpaRepository<Product, Long>{
    fun findByName(name: String): Optional<Product>

}

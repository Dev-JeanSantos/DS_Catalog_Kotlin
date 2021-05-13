package br.com.fourtk.category

import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface CategoryRepository: JpaRepository<Category, Long> {
    fun existsByName(name: String): Boolean

    @Query("SELECT c FROM Category c WHERE c.name = :name")
    fun findByNam(name: String): Optional<Category>
}

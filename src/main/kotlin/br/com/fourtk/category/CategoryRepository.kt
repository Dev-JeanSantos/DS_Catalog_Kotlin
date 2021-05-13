package br.com.fourtk.category

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface CategoryRepository: JpaRepository<Category, Long> {
    fun existsByName(nome: String): Boolean
}

package br.com.fourtk.product

import br.com.fourtk.category.Category
import br.com.fourtk.category.CategoryRepository
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Introspected
data class NewProductRequest (
    @field:NotBlank val name: String,
    @field:NotBlank val description: String,
    @field:NotNull val price: Double,
    @field:NotNull val idCategory: Long,
    val imgUrl: String

        ) {

    fun newProduct(categoryRepository: CategoryRepository): Product {
        val category = categoryRepository.findById(idCategory)
        if (category.isPresent) {
            return Product(name, description, price, imgUrl, category.get())
        }else{
            throw CategoriaNotFound("Category not found")
        }
    }
}

class CategoriaNotFound(s: String) : Exception() {
    override val message: String? = s
}


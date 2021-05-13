package br.com.fourtk.product

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Introspected
data class NewProductRequest (
    @field:NotBlank val name: String,
    @field:NotBlank val description: String,
    @field:NotNull val price: Double,
    val imgUrl: String
        ){

    fun newProduct(): Product {

        return Product(name, description, price, imgUrl)

    }


}

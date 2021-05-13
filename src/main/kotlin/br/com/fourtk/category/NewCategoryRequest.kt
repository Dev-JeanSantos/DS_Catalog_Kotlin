package br.com.fourtk.category

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

@Introspected
data class NewCategoryRequest(
  @field: NotBlank val name : String
) {

    fun newCategory(): Category {

        return Category(name)
    }

}

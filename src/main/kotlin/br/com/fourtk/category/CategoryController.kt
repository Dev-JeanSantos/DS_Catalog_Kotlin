package br.com.fourtk.category

import br.com.fourtk.product.ProductResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.inject.Inject
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/categories")
class CategoryController (@Inject val categoryRepository: CategoryRepository) {

    @Post
    @Transactional
    fun insert(@Body @Valid request: NewCategoryRequest): HttpResponse<Any> {

        val category = request.newCategory()
        categoryRepository.save(category)

        val uri = UriBuilder.of("/categories/{id}")
            .expand(mutableMapOf(Pair("id", category.id)))

        return HttpResponse.created(uri)

    }

    @Get
    fun findAll(): HttpResponse<List<CategoryResponse>>{

        val categories = categoryRepository.findAll()

        val response = categories.map {
                category -> CategoryResponse(category)
        }

        return HttpResponse.ok(response)
    }
}
package br.com.fourtk.category

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
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

        if(categoryRepository.existsByName(request.name)) return HttpResponse.badRequest("Category already exists")

        val category = request.newCategory()
        categoryRepository.save(category)

        val uri = UriBuilder.of("/categories/{id}")
            .expand(mutableMapOf(Pair("id", category.id)))
        return HttpResponse.created(uri)
    }

    @Get
    @Transactional
    fun findAllByName(@QueryValue(defaultValue = "") name: String): HttpResponse<Any> {

        if(name.isBlank()){
            val categories = categoryRepository.findAll()
            val resposta = categories.map { category -> CategoryResponse(
               category.name
            ) }
            return HttpResponse.ok(resposta)
        }

        //Outra Opção com Queries do Hibernate
        val possibleCategory = categoryRepository.findByNam(name)
        if(possibleCategory.isEmpty){
            return HttpResponse.notFound("Author not found")
        }

        val category = possibleCategory.get()
        return HttpResponse.ok(CategoryResponse(
            category.name
        ))
    }
}
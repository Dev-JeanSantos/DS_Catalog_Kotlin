package br.com.fourtk.product

import br.com.fourtk.category.CategoryRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/products")
class ProductController (
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository
) {

    @Post
    @Transactional
    fun insert(@Body @Valid request: NewProductRequest): HttpResponse<Any>{

        val product = request.newProduct(categoryRepository)

        productRepository.save(product)

        val uri = UriBuilder.of("/products/{id}")
            .expand(mutableMapOf(Pair("id", product.id)))

        return HttpResponse.created(uri)
    }

    @Get
    fun findAll(): HttpResponse<List<ProductResponse>>{

        val products = productRepository.findAll()

        val response = products.map {
            product -> ProductResponse(product)
        }

        return HttpResponse.ok(response)
    }
}
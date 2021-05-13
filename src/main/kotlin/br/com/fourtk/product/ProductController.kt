package br.com.fourtk.product

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.inject.Inject
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/products")
class ProductController (@Inject val productRepository: ProductRepository) {

    @Post
    @Transactional
    fun insert(@Body @Valid request: NewProductRequest): HttpResponse<Any>{
        println("DTO => $request")
        val product = request.newProduct()
        println("Classe => ${product.name}")

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
package br.com.fourtk.product

import br.com.fourtk.category.CategoryRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import java.util.*
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
    @Transactional
    fun findAllByName(@QueryValue(defaultValue = "") name: String): HttpResponse<Any> {
        if(name.isBlank()){
            val products = productRepository.findAll()
            val resposta = products.map { product -> ProductResponse(
                product.name,
                product.description,
                product.price,
                product.imgUrl,
                product.category.name
            ) }
            return HttpResponse.ok(resposta)
        }
        //Outra Opção com Queries do Hibernate
        val possibleProduct = productRepository.findByName(name)
        if(possibleProduct.isEmpty){
            return HttpResponse.notFound("Product not found")
        }

        val product = possibleProduct.get()
        return HttpResponse.ok(
            ProductResponse(product.name,product.description, product.price, product.imgUrl,  product.category.name)
        )
    }
    @Put("/{id}")
        @Transactional
        fun update(
            @PathVariable id: Long,
            name: String,
            description: String,
            price: Double,
            imgUrl: String
        ) : HttpResponse<Any>{

            val possibleProduct : Optional<Product> = productRepository.findById(id)

            if (possibleProduct.isEmpty) {
                return HttpResponse.notFound("Product not found")
            }
            val product = possibleProduct.get()
            product.name = name
            product.description = description
            product.price = price
            product.imgUrl = imgUrl
            return HttpResponse.ok(
                ProductResponse(
                product.name, product.description,product.price,product.imgUrl, product.category.name
            )
            )
        }



}
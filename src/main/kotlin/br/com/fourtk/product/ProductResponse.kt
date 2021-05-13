package br.com.fourtk.product

class ProductResponse(product: Product) {
    val name = product.name
    val description = product.description
    val price = product.price
}

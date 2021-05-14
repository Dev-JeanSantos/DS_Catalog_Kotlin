package br.com.fourtk.product

import br.com.fourtk.category.Category

class ProductResponse(
    val name: String,
    val description: String,
    val price: Double,
    val imgUrl: String,
    val category: String
) {

}

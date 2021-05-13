package br.com.fourtk.product

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Product(
    var name: String,
    var description: String,
    var price: Double,
    var imgUrl: String
) {
    @Id
    @GeneratedValue
    var id: Long? = null
}

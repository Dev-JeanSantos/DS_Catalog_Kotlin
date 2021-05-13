package br.com.fourtk.product

import br.com.fourtk.category.Category
import io.micronaut.core.annotation.Introspected
import javax.persistence.*

@Introspected
@Entity
class Product(
    var name: String,
    var description: String,
    var price: Double,
    var imgUrl: String,
    @OneToOne val category: Category
    ) {
    @Id
    @GeneratedValue
    var id: Long? = null
}

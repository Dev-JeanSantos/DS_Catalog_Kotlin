package br.com.fourtk.category

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
class Category(
    var name: String,
    @OneToOne
    var categoria: Category? = null
    ) {
    @Id
    @GeneratedValue
    var id: Long? = null
}

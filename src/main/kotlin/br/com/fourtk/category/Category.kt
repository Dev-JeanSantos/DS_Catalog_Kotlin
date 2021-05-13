package br.com.fourtk.category

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Category(
    var name: String) {
    @Id
    @GeneratedValue
    var id: Long? = null
}

package iut.nantes.project.products.models

import jakarta.persistence.*
import java.util.*

@Entity
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
@NamedQuery(name = "Product.deleteAll", query = "DELETE FROM Product")
@NamedQuery(name = "Product.deleteById", query = "DELETE FROM Product p WHERE p.id = :id")
open class Product(
    @Id
    @GeneratedValue
    val id: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val description: String,

    @Embedded
    val price: Price,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "family", referencedColumnName = "id")
    val family: Family
)
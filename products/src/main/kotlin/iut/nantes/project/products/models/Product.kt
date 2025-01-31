package iut.nantes.project.products.models

import jakarta.persistence.*
import java.util.*

@Entity
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
@NamedQuery(name = "Product.deleteAll", query = "DELETE FROM Product")
@NamedQuery(name = "Product.deleteById", query = "DELETE FROM Product p WHERE p.id = :id")
@NamedQuery(name = "Product.findByFamilyId", query = "SELECT p FROM Product p WHERE p.family = :familyId")
open class Product(
    @Id
    open var id: UUID?,

    @Column(nullable = false)
    open val name: String,

    @Column(nullable = false)
    open val description: String,

    @Embedded
    open val price: Price,

    @OneToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "family", referencedColumnName = "id")
    open val family: Family
)
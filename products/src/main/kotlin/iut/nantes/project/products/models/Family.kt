package iut.nantes.project.products.models

import jakarta.persistence.*
import java.util.*

@Entity
@NamedQuery(name = "Family.findAll", query = "SELECT f FROM Family f")
@NamedQuery(name = "Family.deleteAll", query = "DELETE FROM Family")
@NamedQuery(name = "Family.deleteById", query = "DELETE FROM Family f WHERE f.id = :id")
@NamedQuery(name = "Family.searchByName", query = "SELECT f FROM Family f WHERE f.name = :name")
open class Family(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    open val id: UUID = UUID.randomUUID(),

    @Column(nullable = false, unique = true)
    open val name: String,

    @Column(nullable = false)
    open val description: String,
)
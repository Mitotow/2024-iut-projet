package iut.nantes.project.stores.models

import jakarta.persistence.*

@Entity
open class Store(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open val id: Int,

    @Column(nullable = true, unique = true)
    open val name: String,

    @OneToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "contact", referencedColumnName = "id")
    open val contact: Contact

    // TODO: Liste des produits
)
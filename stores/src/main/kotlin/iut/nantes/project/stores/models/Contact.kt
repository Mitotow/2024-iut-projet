package iut.nantes.project.stores.models

import jakarta.persistence.*

@Entity
open class Contact(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open val id: Int,

    @Column(nullable = false, unique = true)
    open val email: String,

    @Column(nullable = false, unique = true)
    open val phone: String,

    @Embedded
    open val address: Address,
)
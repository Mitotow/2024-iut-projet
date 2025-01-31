package iut.nantes.project.stores.models

import jakarta.persistence.Embeddable

@Embeddable
open class Address(
    val street: String,
    val city: String,
    val postalCode: String,
)
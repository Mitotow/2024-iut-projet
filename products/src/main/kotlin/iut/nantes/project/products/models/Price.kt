package iut.nantes.project.products.models

import jakarta.persistence.Embeddable

@Embeddable
open class Price(
    val amount: Double,
    val currency: String,
)
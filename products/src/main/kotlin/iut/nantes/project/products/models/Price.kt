package iut.nantes.project.products.models

import jakarta.persistence.Embeddable

@Embeddable
data class Price(
    val amount: Double,
    val currency: String,
) {
    companion object {
        val VALID_CURRENCIES = listOf("EUR", "USD", "GBP")
    }
}
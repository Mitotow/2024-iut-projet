package iut.nantes.project.products.dtos

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class PriceDto(
    val amount: Double,

    @field:NotBlank(message = "Currency is required.")
    @field:Pattern(
        regexp = "EUR|USD|GBP",
        message = "Currency must be one of the following : EUR, USD, GBP.",
    )
    val currency: String,
)
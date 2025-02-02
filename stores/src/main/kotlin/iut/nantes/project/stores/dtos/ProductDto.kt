package iut.nantes.project.stores.dtos

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.PositiveOrZero

data class ProductDto(
    val id: Int?,

    @field:NotBlank(message = "Name is required")
    val name: String,

    @field:PositiveOrZero(message = "Quantity cannot be negative")
    val quantity: Int,
)
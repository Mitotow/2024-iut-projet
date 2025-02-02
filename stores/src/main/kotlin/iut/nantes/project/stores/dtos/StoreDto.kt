package iut.nantes.project.stores.dtos

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank

data class StoreDto(
    val id: Int?,

    @field:NotBlank(message = "Name is required")
    val name: String,

    @field:NotBlank(message = "Contact is required")
    val contact: Int,

    @field:Valid
    val products: List<ProductDto>
)
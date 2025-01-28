package iut.nantes.project.products.dtos

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.util.UUID

data class ProductDto(
    val id: UUID?,

    @field:NotBlank(message = "Name is required")
    @field:Size(
        min = 3,
        max = 100,
        message = "Name must be between 3 and 100 characters"
    )
    val name: String,

    @field:NotBlank(message = "Description is required")
    @field:Size(
        min = 3,
        max = 255,
        message = "Description must be between 3 and 255 characters"
    )
    val description: String,

    @field:Valid
    val price: PriceDto,

    @field:Valid
    val family: FamilyDto,
) {
    companion object {
        val SAMPLE = ProductDto(
            UUID.randomUUID(),
            "Sample product",
            "Product's sample description",
            PriceDto(10.0, "EUR"),
            FamilyDto(UUID.randomUUID(), "Samples", "Family of samples"),
        )
    }
}
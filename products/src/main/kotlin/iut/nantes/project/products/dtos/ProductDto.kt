package iut.nantes.project.products.dtos

import iut.nantes.project.products.annotations.ValidUUID
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class ProductDto(
    @ValidUUID
    val id: String?,

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

    @ValidUUID
    val familyId: String,
)
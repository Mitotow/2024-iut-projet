package iut.nantes.project.products.dtos

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.UUID

class FamilyDto(
    @field:UUID
    var id: String?,

    @field:NotBlank(message = "Name is required")
    @field:Size(
        min = 1,
        max = 50,
        message = "Name must be between 1 and 50 characters"
    )
    var name: String,

    @field:NotBlank(message = "Description is required")
    @field:Size(
        min = 1,
        max = 255,
        message = "Description must be between 1 and 255 characters"
    )
    var description: String,
)
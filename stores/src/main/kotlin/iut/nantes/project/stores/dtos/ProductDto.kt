package iut.nantes.project.stores.dtos

import iut.nantes.project.stores.annotations.ValidUUID
import iut.nantes.project.stores.interfaces.IDtoToEntity
import iut.nantes.project.stores.models.Product
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import java.util.UUID

data class ProductDto(
    @ValidUUID
    var id: String,
    @field:NotBlank(message = "Name is required")
    var name: String,
    @field:Positive(message = "Quantity cannot be null or negative")
    var quantity: Int,
): IDtoToEntity<Product> {
    override fun createEntity(): Product {
        return Product(UUID.fromString(id), name, quantity)
    }
}
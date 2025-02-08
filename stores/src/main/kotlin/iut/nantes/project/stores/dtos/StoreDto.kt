package iut.nantes.project.stores.dtos

import iut.nantes.project.stores.interfaces.IDtoToEntity
import iut.nantes.project.stores.models.Store
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class StoreDto(
    var id: Long?,

    @field:NotBlank(message = "Name is required")
    var name: String,

    @field:NotNull
    @field:Valid
    var contact: ContactDto,

    @field:Valid
    var products: List<ProductDto>
): IDtoToEntity<Store> {
    override fun createEntity(): Store {
        return Store(
            this.id,
            this.name,
            this.contact.createEntity(),
            this.products.map { it.createEntity() }.toMutableList(),
        )
    }
}
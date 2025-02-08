package iut.nantes.project.stores.models

import iut.nantes.project.stores.dtos.ProductDto
import iut.nantes.project.stores.interfaces.IEntityToDto
import jakarta.persistence.*
import java.util.UUID

@Entity
open class Product(
    @Id
    var id: UUID,

    @Column(nullable = false, unique = true)
    var name: String,

    @Column(nullable = false)
    var quantity: Int = 1,
): IEntityToDto<ProductDto> {
    override fun createDto(): ProductDto {
        return ProductDto(id.toString(), name, quantity)
    }
}
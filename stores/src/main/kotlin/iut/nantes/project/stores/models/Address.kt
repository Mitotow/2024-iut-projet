package iut.nantes.project.stores.models

import iut.nantes.project.stores.dtos.AddressDto
import iut.nantes.project.stores.interfaces.IEntityToDto
import jakarta.persistence.*

@Entity
open class Address(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var street: String,
    var city: String,
    var postalCode: String,
): IEntityToDto<AddressDto> {
    override fun createDto(): AddressDto {
        return AddressDto(
            street = this.street,
            city = this.city,
            postalCode = this.postalCode,
        )
    }
}
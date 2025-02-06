package iut.nantes.project.stores.dtos

import iut.nantes.project.stores.interfaces.IDtoToEntity
import iut.nantes.project.stores.models.Address

data class AddressDto(
    var street: String,
    var city: String,
    var postalCode: String,
): IDtoToEntity<Address> {
    override fun createEntity(): Address {
        return Address(null, street, city, postalCode)
    }
}
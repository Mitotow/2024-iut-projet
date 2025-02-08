package iut.nantes.project.stores.dtos

import iut.nantes.project.stores.interfaces.IDtoToEntity
import iut.nantes.project.stores.models.Contact
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size

data class ContactDto(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,

    @Email(message = "Invalid Email")
    var email: String,

    @Size(min = 10, max = 10)
    var phone: String,

    @field:Valid
    var address: AddressDto,
): IDtoToEntity<Contact> {
    override fun createEntity(): Contact {
        return Contact(id, email, phone, address.createEntity())
    }
}
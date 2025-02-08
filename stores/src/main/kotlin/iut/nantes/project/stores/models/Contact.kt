package iut.nantes.project.stores.models

import iut.nantes.project.stores.dtos.ContactDto
import iut.nantes.project.stores.interfaces.IEntityToDto
import jakarta.persistence.*

@Entity
open class Contact(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = false, unique = true)
    var phone: String,

    @OneToOne(cascade = [(CascadeType.ALL)])
    @JoinColumn(name = "address", referencedColumnName = "id")
    var address: Address,
): IEntityToDto<ContactDto> {
    override fun createDto(): ContactDto {
        return ContactDto(
            id = this.id,
            email = this.email,
            phone = this.phone,
            address = this.address.createDto(),
        )
    }
}
package iut.nantes.project.stores.models

import jakarta.persistence.*

@Entity
@NamedQuery(name = "Contact.findAll", query = "SELECT c from Contact c")
@NamedQuery(name = "Contact.deleteById", query = "DELETE FROM Contact c WHERE c.id=:id")
@NamedQuery(name = "Contact.deleteAll", query = "DELETE FROM Contact c")
open class Contact(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open val id: Int,

    @Column(nullable = false, unique = true)
    open val email: String,

    @Column(nullable = false, unique = true)
    open val phone: String,

    @Embedded
    open val address: Address,
)
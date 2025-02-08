package iut.nantes.project.stores.models

import iut.nantes.project.stores.dtos.StoreDto
import iut.nantes.project.stores.interfaces.IEntityToDto
import jakarta.persistence.*

@Entity
open class Store(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,

    @Column(nullable = false, unique = true)
    var name: String,

    @ManyToOne
    @JoinColumn(name = "contact", referencedColumnName = "id")
    var contact: Contact,

    @OneToMany(cascade = [CascadeType.ALL])
    var products: MutableList<Product> = mutableListOf()
): IEntityToDto<StoreDto> {
    override fun createDto(): StoreDto {
        return StoreDto(
            id = this.id,
            name = this.name,
            contact = this.contact.createDto(),
            products = this.products.map { it.createDto() },
        )
    }
}
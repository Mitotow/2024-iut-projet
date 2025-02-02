package iut.nantes.project.stores.models

import jakarta.persistence.*

@Entity
@NamedQuery(name = "Store.findAll", query = "SELECT s from Store s")
@NamedQuery(name = "Store.deleteById", query = "DELETE FROM Store s WHERE s.id=:id")
@NamedQuery(name = "Store.deleteAll", query = "DELETE FROM Store s")
open class Store(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Int,

    @Column(nullable = false, unique = true)
    open val name: String,

    @OneToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "contact", referencedColumnName = "id")
    open val contact: Contact,

    @OneToMany(mappedBy = "store", cascade = [CascadeType.MERGE])
    open val products: Set<Product> = emptySet()
)
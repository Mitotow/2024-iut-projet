package iut.nantes.project.products.repositories

import iut.nantes.project.products.models.Product
import iut.nantes.project.products.interfaces.IRepository
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional
import org.springframework.context.annotation.Profile
import java.util.*

@Profile("!dev")
open class JpaProductRepository: IRepository<Product, UUID> {
    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @Transactional
    override fun save(entity: Product) { entityManager.merge(entity) }

    override fun findAll(): MutableList<Product> = entityManager.createNamedQuery("Product.findAll", Product::class.java).resultList

    override fun findById(id: UUID): Optional<Product> = Optional.ofNullable(entityManager.find(Product::class.java, id))

    @Transactional
    override fun deleteAll() {
        val query = entityManager.createNamedQuery("Product.deleteAll", Product::class.java)
        query.executeUpdate()
    }

    @Transactional
    override fun deleteById(id: UUID) {
        val query = entityManager.createNamedQuery("Product.deleteById", Product::class.java)
        query.setParameter("id", id)
        query.executeUpdate()
    }
}
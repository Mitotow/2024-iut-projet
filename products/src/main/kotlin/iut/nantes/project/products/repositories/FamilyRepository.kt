package iut.nantes.project.products.repositories

import iut.nantes.project.products.models.Family
import iut.nantes.project.products.interfaces.IRepository
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional
import java.util.*

open class FamilyRepository: IRepository<Family, UUID> {
    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @Transactional
    override fun save(entity: Family) = entityManager.persist(entity)

    override fun findAll(): MutableList<Family> = entityManager.createNamedQuery("Family.findAll", Family::class.java).resultList

    override fun findById(id: UUID): Optional<Family> = Optional.ofNullable(entityManager.find(Family::class.java, id))

    @Transactional
    override fun deleteAll() {
        val query = entityManager.createNamedQuery("Family.deleteAll", Family::class.java)
        query.executeUpdate()
    }

    @Transactional
    override fun deleteById(id: UUID) {
        val query = entityManager.createNamedQuery("Family.deleteById", Family::class.java)
        query.setParameter("id", id)
        query.executeUpdate()
    }
}
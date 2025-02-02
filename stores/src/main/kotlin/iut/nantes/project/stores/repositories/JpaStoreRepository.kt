package iut.nantes.project.stores.repositories

import iut.nantes.project.stores.interfaces.IStoreRepository
import iut.nantes.project.stores.models.Store
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class JpaStoreRepository: IStoreRepository {
    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @Transactional
    override fun save(entity: Store) = Optional.ofNullable(entityManager.merge(entity))

    override fun findAll(): MutableList<Store> = entityManager.createNamedQuery("Store.findAll", Store::class.java).resultList

    override fun findById(id: Int): Optional<Store> = Optional.ofNullable(entityManager.find(Store::class.java, id))

    @Transactional
    override fun deleteById(id: Int) {
        entityManager.createNamedQuery("Store.deleteById")
            .setParameter("id", id)
            .executeUpdate()
    }

    @Transactional
    override fun deleteAll() {
        entityManager.createNamedQuery("Store.deleteAll")
            .executeUpdate()
    }
}
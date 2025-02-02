package iut.nantes.project.stores.repositories

import iut.nantes.project.stores.interfaces.IContactRepository
import iut.nantes.project.stores.models.Contact
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class JpaContactRepository: IContactRepository {
    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @Transactional
    override fun save(entity: Contact) = Optional.ofNullable(entityManager.merge(entity))

    override fun findAll(): MutableList<Contact> = entityManager.createNamedQuery("Contact.findAll", Contact::class.java).resultList

    override fun findById(id: Int): Optional<Contact> = Optional.ofNullable(entityManager.find(Contact::class.java, id))

    @Transactional
    override fun deleteById(id: Int) {
        entityManager.createNamedQuery("Contact.deleteById")
            .setParameter("id", id)
            .executeUpdate()
    }

    @Transactional
    override fun deleteAll() {
        entityManager.createNamedQuery("Contact.deleteAll")
            .executeUpdate()
    }
}
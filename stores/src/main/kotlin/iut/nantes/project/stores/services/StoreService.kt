package iut.nantes.project.stores.services

import iut.nantes.project.stores.dtos.StoreDto
import iut.nantes.project.stores.exceptions.DtoFactoryException
import iut.nantes.project.stores.interfaces.IDtoFactory
import iut.nantes.project.stores.models.Contact
import iut.nantes.project.stores.models.Store
import iut.nantes.project.stores.repositories.JpaContactRepository
import iut.nantes.project.stores.repositories.JpaStoreRepository
import org.springframework.stereotype.Service

@Service
class StoreService(
    private val storeRepository: JpaStoreRepository,
    private val contactRepository: JpaContactRepository,
): IDtoFactory<StoreDto, Store> {
    fun getAllStores() = storeRepository.findAll()

    fun getStoreById(id: Int) = storeRepository.findById(id)

    fun createStore(store: Store) = storeRepository.save(store)

    fun updateStore(store: Store) = storeRepository.save(store)

    fun deleteStore(id: Int) = storeRepository.deleteById(id)

    override fun createFromDto(dto: StoreDto): Store {
        val contact = contactRepository.findById(dto.contact)
        if (!contact.isPresent)
            throw DtoFactoryException("") // TODO: Add message to Messages object

        return Store(
            dto.id,
            dto.name,
            contact.get(),
            emptySet(), // TODO: Product Repository ?
        )
    }
}
package iut.nantes.project.stores.services

import iut.nantes.project.stores.dtos.ContactDto
import iut.nantes.project.stores.dtos.ProductDto
import iut.nantes.project.stores.dtos.StoreDto
import iut.nantes.project.stores.exceptions.DaoException
import iut.nantes.project.stores.exceptions.DtoFactoryException
import iut.nantes.project.stores.interfaces.IDtoToEntity
import iut.nantes.project.stores.models.Contact
import iut.nantes.project.stores.models.Product
import iut.nantes.project.stores.models.Store
import iut.nantes.project.stores.repositories.IStoreRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class StoreService(
    private val storeRepository: IStoreRepository,
    private val contactService: ContactService,
) {
    fun getAllStores() = storeRepository.findAll().map { it.createDto() }

    fun getStoreById(id: Long) = storeRepository.findById(id).orElse(null)?.createDto()

    fun createStore(dto: StoreDto): StoreDto {
        var contact: ContactDto? = null
        if (dto.contact.id != null)
            contact = contactService.getContactById(dto.contact.id!!)
        if (contact == null)
            contact = contactService.createContact(dto.contact)

        dto.contact = contact
        dto.products = mutableListOf()

        val store = storeRepository.save(dto.createEntity())
        return store.createDto()
    }

    fun updateStore(dto: StoreDto): StoreDto {
        var contact: ContactDto = dto.contact
        if (dto.contact.id != null && contactService.getContactById(dto.contact.id!!) != null)
            contact = contactService.updateContact(dto.contact.id!!, dto.contact)

        dto.contact = contact

        val store = storeRepository.save(dto.createEntity())
        return store.createDto()
    }

    fun deleteStore(id: Long) = storeRepository.deleteById(id)

    fun addProductToStore(storeId: Long, dto: ProductDto): ProductDto {
        val store = storeRepository.findById(storeId).orElse(null)
            ?: throw DaoException("Product with id $storeId not found", HttpStatus.NOT_FOUND)
        val uuid = UUID.fromString(dto.id)

        var product = store.products.find { it.id == uuid }
        if (product != null)
            product.quantity += dto.quantity
        else {
            // TODO: Check product existence by calling the products service
            product = Product(uuid, dto.name, dto.quantity)
            store.products.add(product)
            storeRepository.save(store)
        }

        return product.createDto()
    }

    fun removeProductFromStore(storeId: Long, dto: ProductDto): ProductDto {
        val store = storeRepository.findById(storeId).orElse(null)
            ?: throw DaoException("Product with id $storeId not found", HttpStatus.NOT_FOUND)
        val uuid = UUID.fromString(dto.id)

        val product = store.products.find { it.id == uuid }
            ?: throw DaoException("Product not found", HttpStatus.NOT_FOUND)

        if (product.quantity - dto.quantity < 0)
            throw DaoException("Product quantity cannot be negative or null", HttpStatus.CONFLICT)

        product.quantity -= dto.quantity
        storeRepository.save(store)
        return product.createDto()
    }
}
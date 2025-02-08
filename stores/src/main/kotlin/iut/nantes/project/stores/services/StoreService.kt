package iut.nantes.project.stores.services

import iut.nantes.project.stores.dtos.ContactDto
import iut.nantes.project.stores.dtos.ProductDto
import iut.nantes.project.stores.dtos.StoreDto
import iut.nantes.project.stores.exceptions.DaoException
import iut.nantes.project.stores.repositories.IStoreRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import java.util.UUID

@Service
class StoreService(
    private val storeRepository: IStoreRepository,
    private val contactService: ContactService,
    private val webClient: WebClient,
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

    fun addProductToStore(storeId: Long, productId: UUID, qt: Int): ProductDto {
        val store = storeRepository.findById(storeId).orElse(null)
            ?: throw DaoException("Product with id $storeId not found", HttpStatus.NOT_FOUND)

        var product = store.products.find { it.id == productId }
        if (product != null)
            product.quantity += qt
        else {
            val dto = retrieveProduct(productId)
                ?: throw DaoException("Product with id $productId not found", HttpStatus.NOT_FOUND)
            dto.quantity = qt

            product = dto.createEntity()
            store.products.add(product)
            storeRepository.save(store)
        }

        return product.createDto()
    }

    fun removeProductFromStore(storeId: Long, productId: UUID, qt: Int): ProductDto {
        val store = storeRepository.findById(storeId).orElse(null)
            ?: throw DaoException("Product with id $storeId not found", HttpStatus.NOT_FOUND)

        val product = store.products.find { it.id == productId }
            ?: throw DaoException("Product not found", HttpStatus.NOT_FOUND)

        if (product.quantity - qt < 0)
            throw DaoException("Product quantity cannot be negative or null", HttpStatus.CONFLICT)

        product.quantity -= qt
        storeRepository.save(store)
        return product.createDto()
    }

    private fun retrieveProduct(id: UUID): ProductDto? {
        return webClient.get().uri("/products/$id")
            .retrieve().bodyToMono(ProductDto::class.java)
            .block()
    }
}
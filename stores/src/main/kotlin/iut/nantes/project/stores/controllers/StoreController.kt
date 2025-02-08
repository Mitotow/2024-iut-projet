package iut.nantes.project.stores.controllers

import iut.nantes.project.stores.annotations.ValidUUID
import iut.nantes.project.stores.dtos.ProductDto
import iut.nantes.project.stores.dtos.StoreDto
import iut.nantes.project.stores.models.Store
import iut.nantes.project.stores.services.ContactService
import iut.nantes.project.stores.services.StoreService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.UUID

@Validated
@RestController
@RequestMapping("/api/v1/stores")
class StoreController(
    private val storeService: StoreService,
) {
    @GetMapping
    fun findAll() = ResponseEntity.ok(storeService.getAllStores())

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<StoreDto> {
        val store = storeService.getStoreById(id)
        return if (store != null)
            ResponseEntity.ok(store)
        else ResponseEntity.notFound().build()
    }

    @PostMapping
    fun create(@RequestBody dto: StoreDto): ResponseEntity<StoreDto> {
        val store = storeService.createStore(dto)
        val location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(store.id)
            .toUri()

        return ResponseEntity.created(location).body(store)
    }

    @PostMapping("{storeId}/products/{productId}/add")
    fun addProduct(@PathVariable storeId: Long,
                    @PathVariable @ValidUUID productId: String,
                    @RequestParam quantity: Int = 1): ResponseEntity<ProductDto> {
        val quantityValue = if (quantity <= 0) 1 else quantity
        val store = storeService.addProductToStore(storeId, UUID.fromString(productId), quantityValue)
        return ResponseEntity.ok(store)
    }

    @PostMapping("{storeId}/products/{productId}/remove")
    fun rmProduct(@PathVariable storeId: Long,
                   @PathVariable @ValidUUID productId: String,
                   @RequestParam quantity: Int = 1): ResponseEntity<ProductDto> {
        val quantityValue = if (quantity <= 0) 1 else quantity
        val store = storeService.removeProductFromStore(storeId, UUID.fromString(productId), quantityValue)
        return ResponseEntity.ok(store)
    }

    @PutMapping
    fun update(@RequestBody dto: StoreDto): ResponseEntity<StoreDto> {
        if (dto.id == null)
            return ResponseEntity.badRequest().build()
        val store = storeService.getStoreById(dto.id!!)
        if (store != null)
            return ResponseEntity.ok(storeService.updateStore(store))
        return ResponseEntity.notFound().build()
    }

    @DeleteMapping
    fun delete(@RequestParam id: Long): ResponseEntity<Void> {
        val store = storeService.getStoreById(id)
        if (store != null) {
            storeService.deleteStore(id)
            return ResponseEntity.noContent().build()
        } else return ResponseEntity.notFound().build()
    }
}
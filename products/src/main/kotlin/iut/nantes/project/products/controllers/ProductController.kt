package iut.nantes.project.products.controllers

import iut.nantes.project.products.annotations.ValidUUID
import iut.nantes.project.products.dtos.ProductDto
import iut.nantes.project.products.models.Product
import iut.nantes.project.products.services.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*

@RestController
@RequestMapping("/api/v1/products")
class ProductController(
    @Autowired
    private val productService: ProductService
) {
    @GetMapping
    fun findAll() = ResponseEntity.ok(productService.getAllProducts())

    @GetMapping("{id}")
    fun findById(@PathVariable @ValidUUID id: String): ResponseEntity<Product> {
        val uuid = UUID.fromString(id)

        val product = productService.getProductById(uuid)

        return if (product.isPresent) ResponseEntity.ok(product.get())
        else ResponseEntity.notFound().build()
    }

    @PostMapping
    fun create(@RequestBody productDto: ProductDto): ResponseEntity<Product> {
        val p = productService.createProduct(productDto)
        val location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(p.id)
            .toUri()

        return ResponseEntity.created(location).body(p)
    }

    @PutMapping
    fun update(@RequestBody productDto: ProductDto): ResponseEntity<Product> {
        val p = productService.updateProduct(productDto)
        return ResponseEntity.ok(p)
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable @ValidUUID id: String): ResponseEntity<Void> {
        val uuid = UUID.fromString(id)
        productService.deleteById(uuid)
        return ResponseEntity.noContent().build()
    }
}
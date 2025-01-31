package iut.nantes.project.products.controllers

import iut.nantes.project.products.annotations.ValidUUID
import iut.nantes.project.products.dtos.ProductDto
import iut.nantes.project.products.dtos.ProductFilterDto
import iut.nantes.project.products.models.Product
import iut.nantes.project.products.services.ProductService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*

@Validated
@RestController
@RequestMapping("/api/v1/products")
class ProductController(
    private val productService: ProductService
) {
    @GetMapping
    fun findAll(@Valid @ModelAttribute dto: ProductFilterDto?): ResponseEntity<MutableList<Product>> {
        return if (dto != null)
            ResponseEntity.ok(productService.getProductWithFilter(dto))
        else ResponseEntity.ok(productService.getAllProducts())
    }

    @GetMapping("{id}")
    fun findById(@PathVariable @ValidUUID id: String): ResponseEntity<Product> {
        val uuid = UUID.fromString(id)
        val product = productService.getProductById(uuid)

        return if (product.isPresent) ResponseEntity.ok(product.get())
        else ResponseEntity.notFound().build()
    }

    @PostMapping
    fun create(@RequestBody productDto: ProductDto): ResponseEntity<Product> {
        var product = productService.createFromDto(productDto)
        product = productService.createProduct(product)
        val location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(product.id)
            .toUri()

        return ResponseEntity.created(location).body(product)
    }

    @PutMapping
    fun update(@RequestBody productDto: ProductDto): ResponseEntity<Product> {
        var product = productService.createFromDto(productDto)
        product = productService.updateProduct(product)

        return ResponseEntity.ok(product)
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable @ValidUUID id: String): ResponseEntity<Void> {
        val uuid = UUID.fromString(id)
        productService.deleteById(uuid)

        return ResponseEntity.noContent().build()
    }
}
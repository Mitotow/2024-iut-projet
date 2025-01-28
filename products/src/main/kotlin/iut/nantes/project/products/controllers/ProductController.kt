package iut.nantes.project.products.controllers

import iut.nantes.project.products.annotations.ValidUUID
import iut.nantes.project.products.dtos.ProductDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
class ProductController {
    @GetMapping("/products")
    fun findAll(): ResponseEntity<List<ProductDto>> {
        return ResponseEntity.ok(emptyList())
    }

    @GetMapping("/products/{id}")
    fun findById(@PathVariable @ValidUUID id: String): ResponseEntity<ProductDto?> {
        val uuid = UUID.fromString(id)

        // TODO: Call API
        val product = ProductDto.SAMPLE

        return ResponseEntity.ok(product)
    }
}
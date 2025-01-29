package iut.nantes.project.products.controllers

import iut.nantes.project.products.annotations.ValidUUID
import iut.nantes.project.products.dtos.FamilyDto
import iut.nantes.project.products.models.Family
import iut.nantes.project.products.services.FamilyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*

@RestController
@RequestMapping("/api/v1/families")
class FamilyController(
    @Autowired
    private val familyService: FamilyService
) {
    @GetMapping
    fun findAll() = ResponseEntity.ok(familyService.getAllFamilies())

    @GetMapping("{id}")
    fun findById(@PathVariable @ValidUUID id: String): ResponseEntity<Family> {
        val uuid = UUID.fromString(id)
        val f = familyService.getFamilyById(uuid)

        return if (f.isPresent)
            ResponseEntity.ok(f.get())
        else ResponseEntity.notFound().build()
    }

    @PostMapping
    fun create(@RequestBody dto: FamilyDto): ResponseEntity<Family> {
        val f = familyService.createFamily(dto)

        val location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(f.id)
            .toUri()

        return ResponseEntity.created(location).body(f)
    }

    @PutMapping
    fun update(@RequestBody dto: FamilyDto): ResponseEntity<Family> {
        val f = familyService.updateFamily(dto)
        return ResponseEntity.ok(f)
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable @ValidUUID id: String): ResponseEntity<Void> {
        val uuid = UUID.fromString(id)
        familyService.deleteById(uuid)
        return ResponseEntity.noContent().build()
    }
}
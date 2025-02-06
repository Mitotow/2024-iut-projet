package iut.nantes.project.stores.controllers

import iut.nantes.project.stores.dtos.ContactDto
import iut.nantes.project.stores.models.Contact
import iut.nantes.project.stores.services.ContactService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api/v1/contacts")
class ContactController(
    private val contactService: ContactService,
) {
    @GetMapping
    fun findAll() = ResponseEntity.ok(contactService.getAllContacts())

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<ContactDto> {
        val contact = contactService.getContactById(id)
        return if (contact != null)
            ResponseEntity.ok(contact)
        else ResponseEntity.badRequest().build()
    }

    @PostMapping
    fun create(@RequestBody dto: ContactDto): ResponseEntity<ContactDto> {
        val contact = contactService.createContact(dto)
        val location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(contact.id)
            .toUri()

        return ResponseEntity.created(location).body(contact)
    }

    @PutMapping
    fun update(@RequestBody dto: ContactDto): ResponseEntity<ContactDto> {
        if (dto.id == null)
            return ResponseEntity.badRequest().build()

        val contact = contactService.updateContact(dto.id!!, dto)
        return ResponseEntity.ok(contact)
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        if (contactService.getContactById(id) == null)
            return ResponseEntity.notFound().build()

        contactService.deleteContact(id)
        return ResponseEntity.noContent().build()
    }
}
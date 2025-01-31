package iut.nantes.project.stores.controllers

import iut.nantes.project.stores.services.ContactService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/contacts")
class ContactController(
    private val contactService: ContactService,
) {
}
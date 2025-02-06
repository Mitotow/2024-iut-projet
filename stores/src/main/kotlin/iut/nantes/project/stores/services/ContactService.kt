package iut.nantes.project.stores.services

import iut.nantes.project.stores.dtos.ContactDto
import iut.nantes.project.stores.exceptions.DaoException
import iut.nantes.project.stores.repositories.IContactRepository
import org.springframework.stereotype.Service

@Service
class ContactService(
    private val contactRepository: IContactRepository,
) {
    fun getAllContacts() = contactRepository.findAll().map { it.createDto() }

    fun getContactById(id: Long): ContactDto? {
        val contact = contactRepository.findById(id).orElse(null)
        return contact?.createDto()
    }

    fun createContact(dto: ContactDto): ContactDto {
        if (dto.id != null && contactRepository.findById(dto.id!!). isPresent)
            throw DaoException("Contact with id ${dto.id} already exists.")

        val contact = contactRepository.save(dto.createEntity())
        return contact.createDto()
    }

    fun updateContact(id: Long, dto: ContactDto): ContactDto {
        val contact = contactRepository.save(dto.createEntity())
        return contact.createDto()
    }

    fun deleteContact(id: Long) = contactRepository.deleteById(id)
}
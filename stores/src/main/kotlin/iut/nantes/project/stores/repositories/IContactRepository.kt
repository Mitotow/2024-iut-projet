package iut.nantes.project.stores.repositories

import iut.nantes.project.stores.models.Contact
import org.springframework.data.jpa.repository.JpaRepository

interface IContactRepository: JpaRepository<Contact, Long>
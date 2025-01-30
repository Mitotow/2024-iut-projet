package iut.nantes.project.products.services

import iut.nantes.project.products.configs.Messages
import iut.nantes.project.products.dtos.FamilyDto
import iut.nantes.project.products.exceptions.DaoException
import iut.nantes.project.products.interfaces.IDtoFactory
import iut.nantes.project.products.interfaces.IRepository
import iut.nantes.project.products.interfaces.ISearchableByName
import iut.nantes.project.products.models.Family
import iut.nantes.project.products.models.Product
import java.util.UUID

class FamilyService(
    private val productRepository: IRepository<Product, UUID>,
    private val familyRepository: IRepository<Family, UUID>,
): IDtoFactory<FamilyDto, Family> {
    fun getAllFamilies() = familyRepository.findAll()

    fun getFamilyById(id: UUID) = familyRepository.findById(id)

    fun createFamily(dto: FamilyDto): Family {
        if (familyRepository !is ISearchableByName<*>)
            throw Exception()
        if (familyRepository.searchByName(dto.name).isNotEmpty())
            throw DaoException(Messages.FAMILY_NAME_ALREADY_EXISTS)

        val family = createFromDto(dto)
        familyRepository.save(family)
        return family
    }

    fun updateFamily(dto: FamilyDto): Family {
        if (dto.id == null)
            throw DaoException(Messages.FAMILY_NO_ID_GIVEN)

        val family = createFromDto(dto)
        familyRepository.save(family)
        return family
    }

    fun deleteById(id: UUID) = familyRepository.deleteById(id)

    override fun createFromDto(dto: FamilyDto): Family {
        return Family(
            id = if (dto.id != null) UUID.fromString(dto.id) else UUID.randomUUID(),
            name = dto.name,
            description = dto.description
        )
    }
}
package iut.nantes.project.products.services

import iut.nantes.project.products.configs.Messages
import iut.nantes.project.products.dtos.FamilyDto
import iut.nantes.project.products.exceptions.DaoException
import iut.nantes.project.products.interfaces.*
import iut.nantes.project.products.models.Family
import java.util.UUID

class FamilyService(
    private val productRepository: IProductRepository,
    private val familyRepository: IFamilyRepository,
): IDtoFactory<FamilyDto, Family> {
    fun getAllFamilies() = familyRepository.findAll()

    fun getFamilyById(id: UUID) = familyRepository.findById(id)

    fun createFamily(family: Family): Family {
        if (familyRepository.searchByName(family.name).isNotEmpty())
            throw DaoException(Messages.FAMILY_NAME_ALREADY_EXISTS, true)
        if (family.id == null)
            family.id = UUID.randomUUID()

        familyRepository.save(family)
        return family
    }

    fun updateFamily(family: Family): Family {
        if (family.id == null)
            throw DaoException(Messages.FAMILY_NO_ID_GIVEN)
        if (familyRepository.searchByName(family.name).isNotEmpty())
            throw DaoException(Messages.FAMILY_NAME_ALREADY_EXISTS, true)

        familyRepository.save(family)
        return family
    }

    fun deleteById(id: UUID) {
        if (productRepository.findByFamilyId(id).isNotEmpty())
            throw DaoException(Messages.FAMILY_DELETE_FAIL, true)

        familyRepository.deleteById(id)
    }

    override fun createFromDto(dto: FamilyDto): Family {
        val id = if (dto.id != null) UUID.fromString(dto.id) else null
        return Family(
            id = id,
            name = dto.name,
            description = dto.description
        )
    }
}
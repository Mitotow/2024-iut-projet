package iut.nantes.project.products.repositories

import iut.nantes.project.products.interfaces.IRepository
import iut.nantes.project.products.interfaces.ISearchableByName
import iut.nantes.project.products.models.Family
import org.springframework.context.annotation.Profile
import java.util.*

@Profile("dev")
class HmFamilyRepository: ISearchableByName<Family>, IRepository<Family, UUID> {
    private val map = hashMapOf<UUID, Family>()

    override fun save(entity: Family) { map[entity.id] = entity; }

    override fun searchByName(name: String): MutableList<Family> = map.values.filter { it.name == name }.toMutableList()

    override fun findAll(): MutableList<Family> = map.values.toMutableList()

    override fun findById(id: UUID): Optional<Family> = Optional.ofNullable(map[id])

    override fun deleteAll() = map.clear()

    override fun deleteById(id: UUID) { map.remove(id) }
}
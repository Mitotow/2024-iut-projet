package iut.nantes.project.products.repositories

import iut.nantes.project.products.interfaces.IRepository
import iut.nantes.project.products.models.Family
import org.springframework.context.annotation.Profile
import java.util.*

@Profile("dev")
class HmFamilyRepository: IRepository<Family, UUID> {
    private val map = hashMapOf<UUID, Family>()

    override fun save(entity: Family) { map[entity.id] = entity }

    override fun findAll(): MutableList<Family> = map.values.toMutableList()

    override fun findById(id: UUID): Optional<Family> = Optional.ofNullable(map[id])

    override fun deleteAll() = map.clear()

    override fun deleteById(id: UUID) { map.remove(id) }
}
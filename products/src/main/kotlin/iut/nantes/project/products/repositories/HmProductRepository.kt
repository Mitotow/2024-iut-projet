package iut.nantes.project.products.repositories

import iut.nantes.project.products.interfaces.IRepository
import iut.nantes.project.products.models.Product
import org.springframework.context.annotation.Profile
import java.util.*

@Profile("dev")
class HmProductRepository: IRepository<Product, UUID> {
    private val map = hashMapOf<UUID, Product>()

    override fun save(entity: Product) { map[entity.id] = entity }

    override fun findAll(): MutableList<Product> = map.values.toMutableList()

    override fun findById(id: UUID): Optional<Product> = Optional.ofNullable(map[id])

    override fun deleteAll() = map.clear()

    override fun deleteById(id: UUID) { map.remove(id) }
}
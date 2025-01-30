package iut.nantes.project.products.repositories

import iut.nantes.project.products.dtos.ProductFilterDto
import iut.nantes.project.products.interfaces.IFilterable
import iut.nantes.project.products.interfaces.IRepository
import iut.nantes.project.products.models.Product
import org.springframework.context.annotation.Profile
import java.util.*

@Profile("dev")
class HmProductRepository: IFilterable<Product, ProductFilterDto>, IRepository<Product, UUID> {
    private val map = hashMapOf<UUID, Product>()

    override fun save(entity: Product) { map[entity.id] = entity }

    override fun findAll(): MutableList<Product> = map.values.toMutableList()

    override fun findById(id: UUID): Optional<Product> = Optional.ofNullable(map[id])

    override fun <F> findWithFilter(filter: F): MutableList<Product> {
        if (filter is ProductFilterDto) {
            return map.values.filter {
                (filter.familyname == null || it.family.name == filter.familyname) &&
                (filter.minprice == null || it.price.amount >= filter.minprice) &&
                (filter.maxprice == null || it.price.amount <= filter.maxprice)
            }.toMutableList()
        }
        return mutableListOf()
    }

    override fun deleteAll() = map.clear()

    override fun deleteById(id: UUID) { map.remove(id) }
}
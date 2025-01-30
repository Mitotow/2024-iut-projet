package iut.nantes.project.products.repositories

import iut.nantes.project.products.dtos.ProductDto
import iut.nantes.project.products.dtos.ProductFilterDto
import iut.nantes.project.products.interfaces.IFilterable
import iut.nantes.project.products.models.Product
import iut.nantes.project.products.interfaces.IRepository
import iut.nantes.project.products.interfaces.ISearchableByName
import iut.nantes.project.products.models.Family
import iut.nantes.project.products.models.Price
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.persistence.criteria.*
import jakarta.transaction.Transactional
import org.springframework.context.annotation.Profile
import java.util.*

@Profile("!dev")
open class JpaProductRepository: IFilterable<Product, ProductDto>, IRepository<Product, UUID> {
    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @Transactional
    override fun save(entity: Product) { entityManager.merge(entity) }

    override fun findAll(): MutableList<Product> = entityManager.createNamedQuery("Product.findAll", Product::class.java).resultList

    override fun findById(id: UUID): Optional<Product> = Optional.ofNullable(entityManager.find(Product::class.java, id))

    override fun <F> findWithFilter(filter: F): MutableList<Product> {
        if (filter is ProductFilterDto) {
            val criteriaBuilder: CriteriaBuilder = entityManager.criteriaBuilder
            val criteriaQuery: CriteriaQuery<Product> = criteriaBuilder.createQuery(Product::class.java)
            val root: Root<Product> = criteriaQuery.from(Product::class.java)
            val familyJoin = root.join<Product, Family>("family", JoinType.INNER)
            val predicates: MutableList<Predicate> = mutableListOf()

            filter.familyname?.let { predicates.add(criteriaBuilder.equal(familyJoin.get<String>("name"), it)) }
            filter.minprice?.let { predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get<Price>("price").get("amount"), it.toDouble())) }
            filter.maxprice?.let { predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get<Price>("price").get("amount"), it.toDouble())) }

            criteriaQuery.where(*predicates.toTypedArray())

            return entityManager.createQuery(criteriaQuery).resultList
        }

        return mutableListOf()
    }


    @Transactional
    override fun deleteAll() {
        val query = entityManager.createNamedQuery("Product.deleteAll", Product::class.java)
        query.executeUpdate()
    }

    @Transactional
    override fun deleteById(id: UUID) {
        val query = entityManager.createNamedQuery("Product.deleteById", Product::class.java)
        query.setParameter("id", id)
        query.executeUpdate()
    }
}
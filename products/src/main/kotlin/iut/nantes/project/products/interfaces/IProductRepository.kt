package iut.nantes.project.products.interfaces

import iut.nantes.project.products.dtos.ProductFilterDto
import iut.nantes.project.products.models.Product
import java.util.UUID

interface IProductRepository: IFilterable<Product, ProductFilterDto>, IRepository<Product, UUID> {
    fun findByFamilyId(id: UUID): MutableList<Product>
}
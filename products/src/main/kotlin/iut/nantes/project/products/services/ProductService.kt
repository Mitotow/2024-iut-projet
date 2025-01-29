package iut.nantes.project.products.services

import iut.nantes.project.products.dtos.ProductDto
import iut.nantes.project.products.exceptions.DaoException
import iut.nantes.project.products.exceptions.DtoFactoryException
import iut.nantes.project.products.interfaces.IDtoFactory
import iut.nantes.project.products.interfaces.IRepository
import iut.nantes.project.products.models.Family
import iut.nantes.project.products.models.Price
import iut.nantes.project.products.models.Product
import java.util.UUID

class ProductService(
    private val productRepository: IRepository<Product, UUID>,
    private val familyRepository: IRepository<Family, UUID>,
): IDtoFactory<ProductDto, Product> {
    fun getAllProducts() = productRepository.findAll()

    fun getProductById(id: UUID) = productRepository.findById(id)

    fun createProduct(dto: ProductDto): Product {
        val product = createFromDto(dto)
        productRepository.save(product)
        return product
    }

    fun updateProduct(dto: ProductDto): Product {
        val product = createFromDto(dto)
        if (!productRepository.findById(product.id).isPresent)
            throw DaoException("Product ${dto.id} not found")

        productRepository.save(product)
        return product
    }

    fun deleteById(id: UUID) {
        if (!productRepository.findById(id).isPresent)
            throw DaoException("Product $id not found")
        else productRepository.deleteById(id)
    }

    override fun createFromDto(dto: ProductDto): Product {
        val family = familyRepository.findById(UUID.fromString(dto.familyId))
        if (!family.isPresent)
            throw DtoFactoryException("Family does not exists.")

        return Product(
            id = if (dto.id != null) UUID.fromString(dto.id) else UUID.randomUUID(),
            name = dto.name,
            description = dto.description,
            price = Price(dto.price.amount, dto.price.currency),
            family = family.get(),
        )
    }
}
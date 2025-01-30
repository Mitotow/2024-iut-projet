package iut.nantes.project.products.services

import iut.nantes.project.products.configs.Messages
import iut.nantes.project.products.dtos.ProductDto
import iut.nantes.project.products.dtos.ProductFilterDto
import iut.nantes.project.products.exceptions.DaoException
import iut.nantes.project.products.exceptions.DtoFactoryException
import iut.nantes.project.products.interfaces.IDtoFactory
import iut.nantes.project.products.interfaces.IFilterable
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

    @Suppress("UNCHECKED_CAST")
    fun getProductWithFilter(filter: ProductFilterDto): MutableList<Product> {
        if (productRepository !is IFilterable<*, *>)
            throw Exception()
        return productRepository.findWithFilter(filter) as MutableList<Product>
    }


    fun createProduct(dto: ProductDto): Product {
        val product = createFromDto(dto)
        productRepository.save(product)
        return product
    }

    fun updateProduct(dto: ProductDto): Product {
        val product = createFromDto(dto)
        if (!productRepository.findById(product.id).isPresent)
            throw DaoException(Messages.PRODUCT_NOT_FOUND)

        productRepository.save(product)
        return product
    }

    fun deleteById(id: UUID) {
        if (!productRepository.findById(id).isPresent)
            throw DaoException(Messages.PRODUCT_NOT_FOUND)
        else productRepository.deleteById(id)
    }

    override fun createFromDto(dto: ProductDto): Product {
        val family = familyRepository.findById(UUID.fromString(dto.familyId))
        if (!family.isPresent)
            throw DtoFactoryException(Messages.FAMILY_DOES_NOT_EXIST)

        return Product(
            id = if (dto.id != null) UUID.fromString(dto.id) else UUID.randomUUID(),
            name = dto.name,
            description = dto.description,
            price = Price(dto.price.amount, dto.price.currency),
            family = family.get(),
        )
    }
}
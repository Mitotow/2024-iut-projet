package iut.nantes.project.products.services

import iut.nantes.project.products.configs.Messages
import iut.nantes.project.products.dtos.ProductDto
import iut.nantes.project.products.dtos.ProductFilterDto
import iut.nantes.project.products.exceptions.DaoException
import iut.nantes.project.products.exceptions.DtoFactoryException
import iut.nantes.project.products.interfaces.*
import iut.nantes.project.products.models.Family
import iut.nantes.project.products.models.Price
import iut.nantes.project.products.models.Product
import org.springframework.web.reactive.function.client.WebClient
import java.util.UUID

class ProductService(
    private val productRepository: IProductRepository,
    private val familyRepository: IFamilyRepository,
    private val webClient: WebClient,
): IDtoFactory<ProductDto, Product> {
    fun getAllProducts() = productRepository.findAll()

    fun getProductById(id: UUID) = productRepository.findById(id)

    fun getProductWithFilter(filter: ProductFilterDto): MutableList<Product> = productRepository.findWithFilter(filter)

    fun createProduct(product: Product): Product {
        if (product.id == null)
            product.id = UUID.randomUUID()

        productRepository.save(product)
        return product
    }

    fun updateProduct(product: Product): Product {
        if (product.id == null)
            throw DaoException(Messages.NO_ID_GIVEN)
        if (!productRepository.findById(product.id!!).isPresent)
            throw DaoException(Messages.PRODUCT_NOT_FOUND)

        productRepository.save(product)
        return product
    }

    fun deleteById(id: UUID) {
        if (!productRepository.findById(id).isPresent)
            throw DaoException(Messages.PRODUCT_NOT_FOUND)

        // TODO: Appel vers le service Store pour vérifier si le produit est encore en stock dans un magasin ou non

        else productRepository.deleteById(id)
    }

    override fun createFromDto(dto: ProductDto): Product {
        val id = if (dto.id != null) UUID.fromString(dto.id) else null
        val family = familyRepository.findById(UUID.fromString(dto.familyId))
        if (!family.isPresent)
            throw DtoFactoryException(Messages.FAMILY_DOES_NOT_EXIST)

        return Product(
            id = id,
            name = dto.name,
            description = dto.description,
            price = Price(dto.price.amount, dto.price.currency),
            family = family.get(),
        )
    }
}
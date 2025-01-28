package iut.nantes.project.products.services

import iut.nantes.project.products.interfaces.IRepository
import iut.nantes.project.products.models.Family
import iut.nantes.project.products.models.Product
import java.util.UUID

class FamilyService(
    private val productRepository: IRepository<Product, UUID>,
    private val familyRepository: IRepository<Family, UUID>,
) {
}
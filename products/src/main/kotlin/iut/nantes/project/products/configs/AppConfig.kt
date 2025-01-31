package iut.nantes.project.products.configs

import iut.nantes.project.products.interceptors.UserHeaderInterceptor
import iut.nantes.project.products.interfaces.IFamilyRepository
import iut.nantes.project.products.interfaces.IProductRepository
import iut.nantes.project.products.interfaces.IRepository
import iut.nantes.project.products.models.Family
import iut.nantes.project.products.models.Product
import iut.nantes.project.products.repositories.HmFamilyRepository
import iut.nantes.project.products.repositories.HmProductRepository
import iut.nantes.project.products.repositories.JpaFamilyRepository
import iut.nantes.project.products.repositories.JpaProductRepository
import iut.nantes.project.products.services.FamilyService
import iut.nantes.project.products.services.ProductService
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.*
import org.springframework.web.reactive.function.client.WebClient
import java.util.UUID

@Configuration
class AppConfig {
    // Repositories
    @Bean
    @Profile("!dev")
    fun productRepository(): IProductRepository = JpaProductRepository()

    @Bean
    @Profile("dev")
    fun productRepositoryDev(): IProductRepository = HmProductRepository()

    @Bean
    @Profile("!dev")
    fun familyRepository(): IFamilyRepository = JpaFamilyRepository()

    @Bean
    @Profile("dev")
    fun familyRepositoryDev(): IFamilyRepository = HmFamilyRepository()

    // Services
    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    fun productService(
        productRepository: IProductRepository,
        familyRepository: IFamilyRepository,
        webClient: WebClient,
    ) = ProductService(productRepository, familyRepository, webClient)

    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    fun familyService(
        productRepository: IProductRepository,
        familyRepository: IFamilyRepository,
    ) = FamilyService(productRepository, familyRepository)
}
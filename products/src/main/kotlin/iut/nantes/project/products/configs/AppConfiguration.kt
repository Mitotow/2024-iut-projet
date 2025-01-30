package iut.nantes.project.products.configs

import iut.nantes.project.products.interceptors.UserHeaderInterceptor
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
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.util.UUID

@Configuration
class AppConfiguration: WebMvcConfigurer {
    // Repositories
    @Bean
    @Profile("!dev")
    fun productRepository(): IRepository<Product, UUID> = JpaProductRepository()

    @Bean
    @Profile("dev")
    fun productRepositoryDev(): IRepository<Product, UUID> = HmProductRepository()

    @Bean
    @Profile("!dev")
    fun familyRepository(): IRepository<Family, UUID> = JpaFamilyRepository()

    @Bean
    @Profile("dev")
    fun familyRepositoryDev(): IRepository<Family, UUID> = HmFamilyRepository()

    // Services
    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    fun productService(
        productRepository: IRepository<Product, UUID>,
        familyRepository: IRepository<Family, UUID>,
    ) = ProductService(productRepository, familyRepository)

    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    fun familyService(
        productRepository: IRepository<Product, UUID>,
        familyRepository: IRepository<Family, UUID>
    ) = FamilyService(productRepository, familyRepository)

    // Interceptors
    @Bean
    fun userHeaderInterceptor() = UserHeaderInterceptor()

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(userHeaderInterceptor())
    }

    companion object {
        fun getContext() =
            AnnotationConfigApplicationContext(AppConfiguration::class.java)
    }
}
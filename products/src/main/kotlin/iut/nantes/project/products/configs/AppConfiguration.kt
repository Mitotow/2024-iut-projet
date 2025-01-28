package iut.nantes.project.products.configs

import iut.nantes.project.products.controllers.ProductController
import iut.nantes.project.products.services.ProductService
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.*

@Configuration
class AppConfiguration {
    // Services
    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    fun productService() = ProductService()

    // Controllers
    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    fun productController() = ProductController()

    companion object {
        fun getContext() =
            AnnotationConfigApplicationContext(AppConfiguration::class.java)
    }
}
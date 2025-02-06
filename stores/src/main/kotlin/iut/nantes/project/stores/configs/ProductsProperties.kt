package iut.nantes.project.stores.configs

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "products")
class ProductsProperties {
    lateinit var url: String
}
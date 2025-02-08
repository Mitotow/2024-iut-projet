package iut.nantes.project.products.configs

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "store")
class StoreProperties {
    lateinit var url: String
}
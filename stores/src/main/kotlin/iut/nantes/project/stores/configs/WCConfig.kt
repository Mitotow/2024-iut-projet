package iut.nantes.project.stores.configs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WCConfig(
    private val productsProperties: ProductsProperties
) {
    @Bean
    fun getWebClient(builder: WebClient.Builder) = builder
        .defaultHeader("X-User", "bypass")
        .baseUrl(productsProperties.url)
        .build()
}
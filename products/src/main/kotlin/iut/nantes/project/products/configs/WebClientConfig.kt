package iut.nantes.project.products.configs

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {
    @Autowired
    lateinit var storeProperties: StoreProperties

    @Bean
    fun webClient(builder: WebClient.Builder) = builder.baseUrl(storeProperties.url).build()
}
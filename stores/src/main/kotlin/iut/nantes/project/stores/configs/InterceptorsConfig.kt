package iut.nantes.project.stores.configs

import iut.nantes.project.stores.interceptors.UserHeaderInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class InterceptorsConfig: WebMvcConfigurer {
    @Bean
    fun userHeaderInterceptor() = UserHeaderInterceptor()

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(userHeaderInterceptor())
    }
}
package iut.nantes.project.gateway.configs

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.filters
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LocatorConfig {
    @Bean
    fun routeLocator(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes {
            Constants.SERVICES.forEach {
                route(it.key) {
                    path("/api/v1/${it.value.prefix}/**")
                    filters {
                        rewritePath("/api/v1/${it.value.prefix}(?<segment>/?.*)", "/api/v1\$\\{segment}")
                        addRequestHeader("X-User", "bypass")
                    }
                    uri(it.value.uri)
                }
            }
        }
    }
}
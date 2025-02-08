package iut.nantes.project.gateway.configs

import iut.nantes.project.gateway.models.Service

object Constants {
    val SERVICES: Map<String, Service> = mapOf(
        "products" to Service("prod", "http://localhost:8081"),
        "stores" to Service("stor", "http://localhost:8082"))
}
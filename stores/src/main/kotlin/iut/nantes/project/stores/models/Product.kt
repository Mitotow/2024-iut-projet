package iut.nantes.project.stores.models

import java.util.UUID

class Product(
    val id: UUID,
    val name: String,
    val quantity: Int,
)
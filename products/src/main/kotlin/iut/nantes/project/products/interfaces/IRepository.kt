package iut.nantes.project.products.interfaces

import java.util.*

interface IRepository<T, ID> {
    fun save(entity: T)
    fun findAll(): MutableList<T>
    fun findById(id: ID): Optional<T>
    fun deleteById(id: ID)
    fun deleteAll()
}
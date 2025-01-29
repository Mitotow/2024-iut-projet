package iut.nantes.project.products.interfaces

interface IDtoFactory<D, T> {
    fun createFromDto(dto: D): T
}
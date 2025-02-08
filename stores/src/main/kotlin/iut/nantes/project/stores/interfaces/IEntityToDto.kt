package iut.nantes.project.stores.interfaces

interface IEntityToDto<D> {
    fun createDto(): D
}
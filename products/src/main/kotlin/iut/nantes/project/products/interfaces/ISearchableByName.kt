package iut.nantes.project.products.interfaces

interface ISearchableByName<T, ID>: IRepository<T, ID> {
    fun searchByName(name: String): MutableList<T>
}
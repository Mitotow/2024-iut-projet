package iut.nantes.project.products.interfaces

interface IFilterable<T, D> {
    fun <D> findWithFilter(filter: D): MutableList<T>
}
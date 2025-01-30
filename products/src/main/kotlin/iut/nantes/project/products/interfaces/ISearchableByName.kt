package iut.nantes.project.products.interfaces

/**
 * Interface définissant la capacité de rechercher des entités par leur nom.
 *
 * @param T Type de l'entité à gérer.
 * @param ID Type de l'identifiant unique de l'entité.
 */
interface ISearchableByName<T> {

    /**
     * Recherche des entités par leur nom.
     * @param name Le nom à rechercher.
     * @return Une liste mutable contenant les entités correspondantes.
     */
    fun searchByName(name: String): MutableList<T>
}

package iut.nantes.project.stores.interfaces

/**
 * Interface définissant une fabrique de transformation d'un DTO en entité.
 *
 * @param D Type du DTO (Data Transfer Object).
 * @param T Type de l'entité résultante.
 */
interface IDtoToEntity<E> {

    /**
     * Crée une entité à partir d'un DTO.
     * @param dto Le DTO à transformer.
     * @return L'entité correspondante.
     */
    fun createEntity(): E
}
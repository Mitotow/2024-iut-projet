package iut.nantes.project.products.interfaces

import java.util.*

/**
 * Interface générique représentant un dépôt (repository) pour la gestion des entités.
 *
 * @param T Type de l'entité à gérer.
 * @param ID Type de l'identifiant unique de l'entité.
 */
interface IRepository<T, ID> {

    /**
     * Enregistre une entité dans le dépôt.
     * @param entity L'entité à sauvegarder.
     */
    fun save(entity: T)

    /**
     * Récupère toutes les entités du dépôt.
     * @return Une liste mutable contenant toutes les entités.
     */
    fun findAll(): MutableList<T>

    /**
     * Recherche une entité par son identifiant.
     * @param id L'identifiant de l'entité.
     * @return Un Optional contenant l'entité si elle existe, sinon un Optional vide.
     */
    fun findById(id: ID): Optional<T>

    /**
     * Supprime une entité du dépôt en fonction de son identifiant.
     * @param id L'identifiant de l'entité à supprimer.
     */
    fun deleteById(id: ID)

    /**
     * Supprime toutes les entités du dépôt.
     */
    fun deleteAll()
}

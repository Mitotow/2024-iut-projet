package iut.nantes.project.stores.repositories

import iut.nantes.project.stores.models.Store
import org.springframework.data.jpa.repository.JpaRepository

interface IStoreRepository: JpaRepository<Store, Long>
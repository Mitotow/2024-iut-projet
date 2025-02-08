package iut.nantes.project.products.interfaces

import iut.nantes.project.products.models.Family
import java.util.UUID

interface IFamilyRepository: ISearchableByName<Family>, IRepository<Family, UUID>
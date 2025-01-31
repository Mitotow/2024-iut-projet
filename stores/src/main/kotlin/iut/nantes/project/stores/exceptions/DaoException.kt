package iut.nantes.project.stores.exceptions

class DaoException(message: String, val conflict: Boolean = false): Exception(message)
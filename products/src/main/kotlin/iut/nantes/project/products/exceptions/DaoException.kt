package iut.nantes.project.products.exceptions

class DaoException(message: String, val conflict: Boolean = false): Exception(message)
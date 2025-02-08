package iut.nantes.project.stores.exceptions

import org.springframework.http.HttpStatus

class DaoException(message: String, val status: HttpStatus = HttpStatus.BAD_REQUEST): Exception(message)
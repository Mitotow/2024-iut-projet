package iut.nantes.project.products.controllers

import iut.nantes.project.products.exceptions.DaoException
import iut.nantes.project.products.exceptions.DtoFactoryException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class AdviceController {
    @ExceptionHandler(DaoException::class)
    fun handleDaoException(e: DaoException) = ResponseEntity.badRequest().body(ErrorBody(HttpStatus.BAD_REQUEST, e.message))

    @ExceptionHandler(DtoFactoryException::class)
    fun handleDtoFactoryException(e: DtoFactoryException) = ResponseEntity.badRequest().body(ErrorBody(HttpStatus.BAD_REQUEST, e.message))

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception) = ResponseEntity.internalServerError().body(ErrorBody(HttpStatus.INTERNAL_SERVER_ERROR, e.message ?: "Internal Server Error"))

    data class ErrorBody(
        val status: HttpStatus,
        val message: String?,
    )
}
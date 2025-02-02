package iut.nantes.project.stores.controllers

import iut.nantes.project.stores.configs.Messages
import iut.nantes.project.stores.exceptions.DaoException
import iut.nantes.project.stores.exceptions.DtoFactoryException
import jakarta.validation.ConstraintViolationException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.resource.NoResourceFoundException

@ControllerAdvice
class AdviceController {
    @Autowired
    private lateinit var env: Environment

    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(DaoException::class)
    fun handleDaoException(e: DaoException) = ResponseEntity.status(if (e.conflict) HttpStatus.CONFLICT else HttpStatus.BAD_REQUEST).body(ErrorBody(HttpStatus.BAD_REQUEST, e.message))

    @ExceptionHandler(DtoFactoryException::class)
    fun handleDtoFactoryException(e: DtoFactoryException) = ResponseEntity.badRequest().body(ErrorBody(HttpStatus.BAD_REQUEST, e.message))

    @ExceptionHandler(ResponseStatusException::class)
    fun handleResponseStatusException(e: ResponseStatusException) = ResponseEntity.status(e.statusCode).body(ErrorBody(e.statusCode as HttpStatus, e.reason))

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(e: ConstraintViolationException) = ResponseEntity.badRequest().body(ErrorBody(HttpStatus.BAD_REQUEST, e.message))

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException) = ResponseEntity.badRequest().body(ErrorBody(HttpStatus.BAD_REQUEST, e.allErrors.map { it.defaultMessage }.joinToString("\n")))

    @ExceptionHandler(NoResourceFoundException::class)
    fun handleNoResourceFoundException(e: NoResourceFoundException) = ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorBody(HttpStatus.NOT_FOUND, Messages.NO_RESOURCE_FOUND))

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception) = ResponseEntity.internalServerError().body(ErrorBody(HttpStatus.INTERNAL_SERVER_ERROR, if (env.activeProfiles.contains("dev")) { logger.error(e.toString()); e.message } else "Internal Server Error"))

    data class ErrorBody(
        val status: HttpStatus,
        val message: String?,
    )
}
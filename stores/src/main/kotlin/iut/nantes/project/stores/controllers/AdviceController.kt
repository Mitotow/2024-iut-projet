package iut.nantes.project.stores.controllers

import iut.nantes.project.stores.exceptions.DaoException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class AdviceController {
    @Autowired
    private lateinit var env: Environment

    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(DaoException::class)
    fun handleDaoException(e: DaoException) = ResponseEntity.status(if (e.conflict) HttpStatus.CONFLICT else HttpStatus.BAD_REQUEST).body(ErrorBody(HttpStatus.BAD_REQUEST, e.message))

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception) = ResponseEntity.internalServerError().body(ErrorBody(HttpStatus.INTERNAL_SERVER_ERROR, if (env.activeProfiles.contains("dev")) { logger.error(e.toString()); e.message } else "Internal Server Error"))

    data class ErrorBody(
        val status: HttpStatus,
        val message: String?,
    )
}
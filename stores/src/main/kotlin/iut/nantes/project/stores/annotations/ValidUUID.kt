package iut.nantes.project.stores.annotations

import iut.nantes.project.stores.configs.Messages
import iut.nantes.project.stores.validators.UUIDValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UUIDValidator::class])
annotation class ValidUUID(
    val message: String = Messages.UUID_BAD_FORMAT,
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)
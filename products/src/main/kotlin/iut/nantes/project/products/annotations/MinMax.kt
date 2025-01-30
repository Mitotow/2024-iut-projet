package iut.nantes.project.products.annotations

import iut.nantes.project.products.configs.Messages
import iut.nantes.project.products.validators.MinMaxValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [MinMaxValidator::class])
annotation class MinMax (
    val message: String = Messages.MIN_HIGHER_THAN_MAX,
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
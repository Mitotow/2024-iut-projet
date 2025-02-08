package iut.nantes.project.stores.validators

import iut.nantes.project.stores.annotations.ValidUUID
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.util.UUID

class UUIDValidator: ConstraintValidator<ValidUUID, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext): Boolean {
        if (value.isNullOrBlank()) return false
        return try { UUID.fromString(value); true } catch(e: IllegalArgumentException) { false }
    }
}
package iut.nantes.project.products.validators

import iut.nantes.project.products.annotations.MinMax
import iut.nantes.project.products.dtos.ProductFilterDto
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class MinMaxValidator: ConstraintValidator<MinMax, ProductFilterDto> {
    override fun isValid(value: ProductFilterDto?, context: ConstraintValidatorContext?): Boolean {
        if (value?.minprice == null || value.maxprice == null)
            return true
        return value.minprice <= value.maxprice
    }
}
package iut.nantes.project.products.configs

object Messages {
    // Validators
    const val UUID_BAD_FORMAT = "The UUID provided is invalid"
    const val MIN_HIGHER_THAN_MAX = "Minimum price cannot be higher than maximum price"

    // Controllers / Services
    const val PRODUCT_NOT_FOUND = "The requested product was not found"
    const val FAMILY_DOES_NOT_EXIST = "The specified family does not exist"
    const val FAMILY_NAME_ALREADY_EXISTS = "A family with this name already exists"
    const val FAMILY_NO_ID_GIVEN = "Missing family ID for the update"

    // Dtos
    const val PRODUCT_FILTER_MINPRICE_INVALID = "minprice must be higher than 100 and less than 200 and maxprice"
    const val PRODUCT_FILTER_MAXPRICE_INVALID = "maxprice must be less than 200 and higher than 100 and minprice"

    // Interceptors
    const val USER_HEADER_UNAUTHORIZED = "You are not authorized to access this resource"
}

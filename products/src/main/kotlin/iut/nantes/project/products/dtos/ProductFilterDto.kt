package iut.nantes.project.products.dtos

import iut.nantes.project.products.annotations.MinMax
import iut.nantes.project.products.configs.Messages
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min

@MinMax
data class ProductFilterDto(
    val familyname: String?,

    @field:Min(100, message = Messages.PRODUCT_FILTER_MINPRICE_INVALID)
    val minprice: Int?,

    @field:Min(100, message = Messages.PRODUCT_FILTER_MINPRICE_INVALID)
    @field:Max(200, message = Messages.PRODUCT_FILTER_MINPRICE_INVALID)
    val maxprice: Int?,
)
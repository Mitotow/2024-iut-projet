package iut.nantes.project.products

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan

@SpringBootApplication
@ServletComponentScan
class ProductsApplication

fun main(args: Array<String>) {
    runApplication<ProductsApplication>(*args)
}
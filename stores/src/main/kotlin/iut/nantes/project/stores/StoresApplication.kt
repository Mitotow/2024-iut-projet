package iut.nantes.project.stores

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan

@SpringBootApplication
@ServletComponentScan
class StoresApplication

fun main(args: Array<String>) {
    runApplication<StoresApplication>(*args)
}

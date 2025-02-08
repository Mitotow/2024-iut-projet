package iut.nantes.project.products

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller()
class TestController {
    @GetMapping
    fun index(): ResponseEntity<String> {
        return ResponseEntity.ok("test")
    }
}
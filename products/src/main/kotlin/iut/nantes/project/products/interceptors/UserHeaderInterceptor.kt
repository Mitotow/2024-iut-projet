package iut.nantes.project.products.interceptors

import iut.nantes.project.products.configs.Constants
import iut.nantes.project.products.configs.Messages
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.HandlerInterceptor

class UserHeaderInterceptor: HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (request.getHeader(Constants.USER_HEADER).isNullOrBlank())
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, Messages.USER_HEADER_UNAUTHORIZED)

        return true
    }
}
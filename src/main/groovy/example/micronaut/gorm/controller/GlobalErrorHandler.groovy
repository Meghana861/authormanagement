package example.micronaut.gorm.controller

import example.micronaut.gorm.handlers.AuthorNotFoundException
import example.micronaut.gorm.handlers.BookNotFoundException
import example.micronaut.gorm.handlers.ErrorResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Error

@Controller
class GlobalErrorHandler {
    @Error(global = true,exception = AuthorNotFoundException)
    HttpResponse<ErrorResponse> handleAuthorNotFoundException(AuthorNotFoundException ex) {
        ErrorResponse errorResponse=new ErrorResponse(HttpStatus.NOT_FOUND.code,"Author Not Found",ex.message)
        return HttpResponse.status(HttpStatus.NOT_FOUND).body(errorResponse)
    }
    @Error(global = true,exception = BookNotFoundException)
    HttpResponse<ErrorResponse> handleBookNotFoundException(BookNotFoundException ex) {
        ErrorResponse errorResponse=new ErrorResponse(HttpStatus.NOT_FOUND.code,"Book Not Found",ex.message)
        return HttpResponse.status(HttpStatus.NOT_FOUND).body(errorResponse)
    }
}

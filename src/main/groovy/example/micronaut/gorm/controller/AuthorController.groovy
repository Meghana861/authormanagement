package example.micronaut.gorm.controller


import example.micronaut.gorm.model.AuthorModel
import example.micronaut.gorm.model.BookModel
import example.micronaut.gorm.service.AuthorService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put

import javax.inject.Inject

@Controller("/authors")
class AuthorController {
    @Inject
    AuthorService authorService
    @Post("/create")
    def createAuthors(@Body AuthorModel authorModel){
            return authorService.createAuthor(authorModel)

    }

    @Delete("/{id}")
    HttpResponse deleteAuthors(Long id){
        if(authorService.deleteAuthor(id)){
            return HttpResponse.ok("deleted successfully")
        }
        else {
            return HttpResponse.notFound("Author not found")
        }

    }

    @Get("/getanahuangbooks")
    List<BookModel> getAllAnaBooks() {
        return authorService.getAnaBooks()
    }

    @Get
    def getAllAuthors(){
        return authorService.getAllAuthors()
    }

    @Put("/{id}")
    def updateAuthor(@PathVariable Long id, @Body AuthorModel authormodel){
        return authorService.updateAuthor(id,authormodel)
    }

    @Delete("/deletebook/{id}")
    def deleteBook(@PathVariable Long id){
        authorService.deleteById(id)
        return("deleted successfully ${id}")
    }

    @Get("/getbooks/{id}")
    def getBooksById(@PathVariable Long id){
        authorService.getBookById(id)
    }

    @Get("/getauthors/{id}")
    def getAuthorsById(@PathVariable Long id){
        authorService.getAuthorById(id)
    }
}

package example.micronaut.gorm.handlers

class AuthorSavedException extends RuntimeException{
    AuthorSavedException(String message){
        super(message)
    }
}

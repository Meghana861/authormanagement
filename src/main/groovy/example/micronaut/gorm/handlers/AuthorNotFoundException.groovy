package example.micronaut.gorm.handlers

class AuthorNotFoundException extends RuntimeException{
    AuthorNotFoundException(String message){
        super(message)
    }
}

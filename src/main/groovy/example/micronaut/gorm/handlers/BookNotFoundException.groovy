package example.micronaut.gorm.handlers


class BookNotFoundException extends RuntimeException {
    BookNotFoundException(String message) {
        super(message)
    }
}

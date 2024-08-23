package example.micronaut.gorm.model

class AuthorModel {
    Long id
    String name
    String penName
    int age
    Set<BookModel> books
}

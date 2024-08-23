package example.micronaut.gorm.model

import example.micronaut.gorm.domain.AuthorDomain


class BookModel {
    Long id
    String title
    int price
    Date publishedDate
    AuthorModel author//It serves as a reference or foreign key to link the BookDTO to the AuthorDTO
}

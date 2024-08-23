package example.micronaut.gorm.service

import example.micronaut.gorm.domain.AuthorDomain
import example.micronaut.gorm.domain.BookDomain

import example.micronaut.gorm.model.AuthorModel
import example.micronaut.gorm.model.BookModel
import grails.gorm.transactions.Transactional
import org.hibernate.SessionFactory

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthorService {
    @Inject
    SessionFactory sessionFactory
    def anabooks = "SELECT * FROM book_domain WHERE price = :price"

    /*Saving an Author*/
    @Transactional
    def createAuthor(AuthorModel authorModel){
                // Started with authormodel because this is the object received from a client
                //AuthorDomain authorDomain=AuthorDomain.findByName(authorModel.name) //find by the given name in request so authormodel is used
                AuthorDomain authorDomain = new AuthorDomain()
                //convert the AuthorModel into an AuthorDomain because you need to persist(continue to exist) the data to the database.
                authorDomain.name = authorModel.name//Model to Domain
                authorDomain.penName = authorModel.penName
                authorDomain.age = authorModel.age
                authorDomain.save()
                authorDomain.books = new HashSet<>()//books used in authorDomain while establishing one-many
                authorModel.books.each{
                BookDomain bookDomain=new BookDomain()
                bookDomain.title = it.title
                bookDomain.price = it.price
                bookDomain.publishedDate = it.publishedDate
                //bookDomain.author=authorDomain
                authorDomain.addToBooks(bookDomain)

            bookDomain.save()
            authorDomain.save()
        }

        return "Author Created successfully"
    }

    /*Delete By Id of Author*/
    @Transactional
    def deleteAuthor(Long id){
      AuthorDomain authorDomain=AuthorDomain.findById(id)
        if(authorDomain){
            authorDomain.delete()
            return true
        }
        else{
            return false
        }
    }

    /*Get All Authors By Books*/
    @Transactional
     List<AuthorModel> getAllAuthors() {
        List<AuthorDomain> authorDomains = AuthorDomain.list() //AuthorDomain.list() is equal to SELECT * FROM author_domain
        return authorDomains.collect {
            AuthorModel authorModel=new AuthorModel()
            authorModel.id=it.id
            authorModel.name= it.name
            authorModel.penName= it.penName
            authorModel.age= it.age
                    authorModel.books= it.books.collect { bookDomain ->
                        new BookModel(
                                id:bookDomain.id,
                                title: bookDomain.title,
                                price: bookDomain.price,
                                publishedDate: bookDomain.publishedDate
                        )
                    }
      return authorModel
        }
    }

    /*update By AuthorId*/
    @Transactional
    def updateAuthor(Long id,AuthorModel updatedAuthorModel){
        AuthorDomain authorDomain=AuthorDomain.findById(id)
        authorDomain.name=updatedAuthorModel.name
        authorDomain.penName=updatedAuthorModel.penName
        authorDomain.age=updatedAuthorModel.age
        authorDomain.save()
        updatedAuthorModel.books.each{
            BookDomain bookDomain=new BookDomain()
            bookDomain.title=it.title
            bookDomain.price=it.price
            bookDomain.publishedDate=it.publishedDate
         bookDomain.author=authorDomain
         authorDomain.addToBooks(bookDomain)
            bookDomain.save()
        }
        authorDomain.save()
        return updatedAuthorModel
    }

    /*Custom Query */
    @Transactional
    def getAnaBooks() {
        def session = sessionFactory.getCurrentSession()
        try {
            def query = session.createSQLQuery(anabooks)
            query.setParameter("price", 200)
            def results = query.list()
            return results
        } catch (Exception e) {
            println "Error executing query: ${e.message}"
            e.printStackTrace()
            throw e
        }
    }


    /*Delete Books By id*/
    @Transactional
    def deleteById(Long id){
        BookDomain bookDomain=BookDomain.findById(id)
        bookDomain.author.removeFromBooks(bookDomain)
        /*removeFromBooks method has relationship to allow removing a specific BookDomain instance from the books
        this helps in taking out bookDomain from the authorDomain with the instance of author*/
        bookDomain.delete()
    }

  
}



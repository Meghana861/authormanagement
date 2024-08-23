package example.micronaut.gorm.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import grails.gorm.annotation.Entity
import groovy.transform.ToString

@Entity
//@ToString(includeNames=true) //to provide a string representation of an object.
class AuthorDomain {

    String name
    String penName
    int age
    static hasMany =[books:BookDomain]//one-to-may one author can have many books
    @JsonIgnore
    static mapping={
        books fetch:"join"
    }
    static constraints = {
        name blank: false, nullable: false
        penName blank: false, nullable: false
        age min: 18 // Assuming authors must be at least 18 years old
    }
}


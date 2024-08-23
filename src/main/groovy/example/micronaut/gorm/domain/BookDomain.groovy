package example.micronaut.gorm.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import example.micronaut.gorm.model.AuthorModel
import grails.gorm.annotation.Entity
import groovy.transform.ToString

import javax.persistence.Id

@Entity
//@ToString(includeNames=true)
class BookDomain{

    String title
    int price
    Date publishedDate
    static belongsTo=[author:AuthorDomain]
    /*author property in BookDomain represents the association with AuthorDomain.
    author property is the reference to the associated AuthorDomain object.
    Many-One (Many Books contains one Author)*/
    @JsonIgnore
    static constraints={
        title unique:true,blank:false,nullanle:false
    }

}

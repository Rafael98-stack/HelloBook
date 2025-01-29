package project.HelloBook.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder

public class Author {

    @Id
    @GeneratedValue
    private Long id;
    private String firstname;
    private String lastname;
    private String nationality;

    public Author(String firstname, String lastname, String nationality) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.nationality = nationality;
    }

    @OneToMany(mappedBy = "author")
    private List<Book> books;

}

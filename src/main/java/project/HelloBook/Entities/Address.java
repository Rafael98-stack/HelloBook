package project.HelloBook.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Address {
    @Id
    @GeneratedValue
    private Long id;
    private String street;
    private String city;
    private String zip_code;

    public Address(String street, String city, String zip_code) {
        this.street = street;
        this.city = city;
        this.zip_code = zip_code;
    }

    @OneToMany(mappedBy = "address")
    private List<User> user;
}

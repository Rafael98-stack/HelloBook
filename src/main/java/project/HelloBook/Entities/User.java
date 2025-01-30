package project.HelloBook.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "`user`")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public User(Address address, String password, String email, String lastname, String firstname) {
        this.address = address;
        this.password = password;
        this.email = email;
        this.lastname = lastname;
        this.firstname = firstname;
    }

    @ManyToOne()
    @JoinColumn(name = "id_address")
    private Address address;
}

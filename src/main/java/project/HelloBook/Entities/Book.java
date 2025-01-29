package project.HelloBook.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder

public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private Double price;
    private LocalDate published_date;
    private Integer stock;

    public Book(Author author, Integer stock, LocalDate published_date, Double price, String title) {
        this.author = author;
        this.stock = stock;
        this.published_date = published_date;
        this.price = price;
        this.title = title;
    }

    @ManyToOne()
    @JoinColumn(name = "author_id")
    private Author author;
}

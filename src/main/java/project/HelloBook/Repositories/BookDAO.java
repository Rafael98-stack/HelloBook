package project.HelloBook.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.HelloBook.Entities.Book;

import java.util.Optional;

@Repository
public interface BookDAO extends JpaRepository<Book,Long> {
    @Query(value = "SELECT b.id as book_id, b.title, b.price, b.published_date, b.stock, a.id as author_id, a.firstname, a.lastname, a.nationality FROM Book b JOIN Author a ON b.author_id = a.id WHERE b.title = ?1 AND a.nationality = ?2 LIMIT 1", nativeQuery = true)
    Optional<Book> findByTitleAndAuthorNationality(String title, String nationality);

}

package project.HelloBook.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.HelloBook.Entities.Book;

@Repository
public interface BookDAO extends JpaRepository<Book,Long> {
    @Query(value = "SELECT * FROM Book b JOIN Author a ON b.author_id = a.id WHERE b.title = ?1 AND a.nationality = ?2 LIMIT 1", nativeQuery = true)
    Book findByTitleAndAuthor_Nationality(String title, String nationality);
}

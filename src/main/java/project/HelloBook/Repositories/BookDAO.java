package project.HelloBook.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.HelloBook.Entities.Book;
@Repository
public interface BookDAO extends JpaRepository<Book,Long> {
}

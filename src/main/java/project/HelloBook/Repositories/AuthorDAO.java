package project.HelloBook.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.HelloBook.Entities.Author;
@Repository
public interface AuthorDAO extends JpaRepository<Author,Long> {
}

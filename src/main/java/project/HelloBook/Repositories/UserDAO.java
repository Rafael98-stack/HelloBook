package project.HelloBook.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.HelloBook.Entities.User;
@Repository
public interface UserDAO extends JpaRepository<User,Long> {
}

package project.HelloBook.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.HelloBook.Entities.Address;
@Repository
public interface AddressDAO extends JpaRepository<Address,Long> {
}

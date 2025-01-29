package project.HelloBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import project.HelloBook.Entities.Address;
import project.HelloBook.Entities.Author;
import project.HelloBook.Entities.Book;
import project.HelloBook.Entities.User;
import project.HelloBook.Repositories.AddressDAO;
import project.HelloBook.Repositories.AuthorDAO;
import project.HelloBook.Repositories.BookDAO;
import project.HelloBook.Repositories.UserDAO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class DatabaseSeeder implements CommandLineRunner {
    private final UserDAO userDAO;
    private final AddressDAO addressDAO;
    private final AuthorDAO authorDAO;
    private final BookDAO bookDAO;

    @Autowired
    public DatabaseSeeder(UserDAO userDAO, AddressDAO addressDAO, AuthorDAO authorDAO, BookDAO bookDAO) {
        this.userDAO = userDAO;
        this.addressDAO = addressDAO;
        this.authorDAO = authorDAO;
        this.bookDAO = bookDAO;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Address> addresses = addressDAO.findAll();
        List<Author> authors = authorDAO.findAll();

        if (addressDAO.count() == 0) {
            List<Address> addresse = IntStream.range(0, 1000)
                    .mapToObj(i -> new Address("Street " + i, "City " + i, "Zip" + i))
                    .collect(Collectors.toList());
            addressDAO.saveAll(addresse);
        }
        if (userDAO.count() == 0 && !addresses.isEmpty()) {
            List<User> users = IntStream.range(0, Math.min(1000, addresses.size()))
                    .mapToObj(i -> new User(addresses.get(i), "0" + i, i + "@example.com", "Cognome" + i, "Nome" + i))
                    .collect(Collectors.toList());
            userDAO.saveAll(users);
        }
        if (authorDAO.count() == 0) {
            List<Author> author = IntStream.range(0, 1000)
                    .mapToObj(i -> new Author("nome" + i, "cognome" + i, "nazionalità"+i))
                    .collect(Collectors.toList());
            authorDAO.saveAll(author);
        }

        if (bookDAO.count() == 0 && !authors.isEmpty()) {
            List<Book> books = IntStream.range(0, Math.min(1000, authors.size()))
                    .mapToObj(i -> {
                        Author author = authors.get(i % authors.size()); // Usa modulo per evitare di superare l'indice
                        return new Book(author, i, LocalDate.of(1990 + (i % 30), (i % 12) + 1, (i % 28) + 1), 10.0 + i, "Book Title " + i);
                    })
                    .collect(Collectors.toList());
            bookDAO.saveAll(books);
        }
    }
}

package project.HelloBook.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.HelloBook.Dtos.BookDtos.BookRequestInsert;
import project.HelloBook.Dtos.BookDtos.BookRequestUpdate;
import project.HelloBook.Dtos.BookDtos.BookResponse;
import project.HelloBook.Dtos.BookSelected;
import project.HelloBook.Entities.Book;
import project.HelloBook.ExceptionHandlers.Exceptions.AuthorNotFoundException;
import project.HelloBook.ExceptionHandlers.Exceptions.BookNotFoundException;
import project.HelloBook.Mappers.BookMapper;
import project.HelloBook.Repositories.AuthorDAO;
import project.HelloBook.Repositories.BookDAO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookDAO bookDAO;
    private final AuthorDAO authorDAO;
    private final BookMapper bookMapper;
    @Autowired
    public BookService(BookDAO bookDAO, AuthorDAO authorDAO, BookMapper bookMapper) {
        this.bookDAO = bookDAO;
        this.authorDAO = authorDAO;
        this.bookMapper = bookMapper;
    }

    public BookResponse insertBook(BookRequestInsert bookRequestInsert){
        // Mapping method to "unpacking" current dto
        /*
        Book book = bookMapper.fromBookRequestInsert(bookRequestInsert);
        return BookResponse
                .builder()
                .id(bookDAO.save(book).getId())
                .build();
        */
        Book book = new Book(
                authorDAO.findById(bookRequestInsert.id_author())
                        .orElseThrow(()-> new AuthorNotFoundException("Author con id " + bookRequestInsert.id_author() + " non trovato")),
                bookRequestInsert.stock(),
                bookRequestInsert.published_date(),
                bookRequestInsert.price(),
                bookRequestInsert.title()
        );

        return BookResponse
                .builder()
                .id(bookDAO.save(book).getId())
                .build();
    }

    public BookResponse updateBookById(Long id_book, BookRequestUpdate bookRequestUpdate){

        /*
        Book bookUpdated = bookMapper.fromBookRequestUpdate(bookRequestUpdate);
        bookUpdated.setId(book.getId());
        return BookResponse
                .builder()
                .id(bookDAO.save(bookUpdated).getId())
                .build();
        */

        Book book = bookDAO.findById(id_book)
                .orElseThrow(()-> new BookNotFoundException("Book con id " + id_book + " non trovato"));

        book.setAuthor( authorDAO.findById(bookRequestUpdate.id_author())
                        .orElseThrow(()-> new AuthorNotFoundException("Author con id " + bookRequestUpdate.id_author() + " non trovato"))
                );
        book.setStock(bookRequestUpdate.stock());
        book.setPublished_date(bookRequestUpdate.published_date());
        book.setPrice(bookRequestUpdate.price());
        book.setTitle(bookRequestUpdate.title());

        return BookResponse
                .builder()
                .id(bookDAO.save(book).getId())
                .build();
    }

    public BookResponse getBookById(Long id_book){
        return BookResponse
                .builder()
                .id(bookDAO.findById(id_book )
                        .orElseThrow(()-> new BookNotFoundException("Book con id " + id_book + " non trovato")).getId())
                .build();
    }

    public BookSelected getByTitleAndNationality(String title, String nationality) {
        Optional<Book> book = bookDAO.findByTitleAndAuthorNationality(title,nationality);

        return  book.map(current -> BookSelected.builder()
                        .id(current.getId())
                        .title(current.getTitle())
                        .price(current.getPrice())
                        .publishedDate(current.getPublished_date())
                        .stock(current.getStock())
                        .firstname(current.getAuthor().getFirstname())
                        .lastname(current.getAuthor().getLastname())
                        .nationality(current.getAuthor().getNationality())
                        .build())
                .orElseThrow(() -> new BookNotFoundException("No book found with title '" + title + "' and nationality '" + nationality + "'"));

    }

    public List<BookResponse> getAllBooks(){
        List<Book> books = bookDAO.findAll();

        return books.stream()
                .map(book -> new BookResponse(book.getId()))
                .collect(Collectors.toList());

    }

    public void deleteBookById(Long id_book){
        bookDAO.deleteById(id_book);
    }


}

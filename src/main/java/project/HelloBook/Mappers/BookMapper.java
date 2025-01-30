package project.HelloBook.Mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.HelloBook.Dtos.BookDtos.BookRequestInsert;
import project.HelloBook.Dtos.BookDtos.BookRequestUpdate;
import project.HelloBook.Entities.Book;
import project.HelloBook.ExceptionHandlers.Exceptions.AuthorNotFoundException;
import project.HelloBook.Repositories.AuthorDAO;

@Service
public class BookMapper {

    @Autowired
    private AuthorDAO authorDAO;

    public Book fromBookRequestInsert(BookRequestInsert request) {
        return Book
                .builder()
                .author(authorDAO.findById(request.id_author())
                        .orElseThrow(()-> new AuthorNotFoundException("Author non trovato")))
                .stock(request.stock())
                .published_date(request.published_date())
                .price(request.price())
                .title(request.title())
                .build();
    }

    public Book fromBookRequestUpdate(BookRequestUpdate request) {
        return Book
                .builder()
                .author(authorDAO.findById(request.id_author())
                        .orElseThrow(()-> new AuthorNotFoundException("Author non trovato")))
                .stock(request.stock())
                .published_date(request.published_date())
                .price(request.price())
                .title(request.title())
                .build();
    }
}

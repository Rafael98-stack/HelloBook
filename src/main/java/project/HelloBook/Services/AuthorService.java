package project.HelloBook.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.HelloBook.Dtos.AuthorDtos.AuthorRequestInsert;
import project.HelloBook.Dtos.AuthorDtos.AuthorRequestUpdate;
import project.HelloBook.Dtos.AuthorDtos.AuthorResponse;
import project.HelloBook.Entities.Author;
import project.HelloBook.ExceptionHandlers.Exceptions.AuthorNotFoundException;
import project.HelloBook.Mappers.AuthorMapper;
import project.HelloBook.Repositories.AuthorDAO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorMapper authorMapper;
    private final AuthorDAO authorDAO;
    @Autowired
    public AuthorService(AuthorMapper authorMapper, AuthorDAO authorDAO) {
        this.authorMapper = authorMapper;
        this.authorDAO = authorDAO;
    }

    public AuthorResponse insertAuthor(AuthorRequestInsert authorRequestInsert){

        // Mapping method to "unpacking" current dto
        /*
        Author author = authorMapper.fromAuthorRequestInsert(authorRequestInsert);
        return AuthorResponse
                .builder()
                .id(authorDAO.save(author).getId())
                .build();
        */

        Author author = new Author(
                authorRequestInsert.firstname(),
                authorRequestInsert.lastname(),
                authorRequestInsert.nationality()
        );

        return AuthorResponse
                .builder()
                .id(authorDAO.save(author).getId())
                .build();
    }

    public AuthorResponse updateAuthorById(Long id_author, AuthorRequestUpdate authorRequestUpdate){
        Author author = authorDAO.findById(id_author)
                .orElseThrow(()-> new AuthorNotFoundException("Author con id " + id_author + " non trovato"));

        author.setFirstname(authorRequestUpdate.firstname());
        author.setLastname(authorRequestUpdate.lastname());
        author.setNationality(authorRequestUpdate.nationality());

        return AuthorResponse
                .builder()
                .id(authorDAO.save(author).getId())
                .build();
    }

    public AuthorResponse getAuthorById(Long id_author){
        return AuthorResponse
                .builder()
                .id(authorDAO.findById(id_author )
                        .orElseThrow(()-> new AuthorNotFoundException("Author con id " + id_author + " non trovato")).getId())
                .build();
    }

    public List<AuthorResponse> getAllAuthors(){
        List<Author> authors = authorDAO.findAll();

        return authors.stream()
                .map(author -> new AuthorResponse(author.getId()))
                .collect(Collectors.toList());

    }

    public void deleteAuthorById(Long id_author){
        authorDAO.deleteById(id_author);
    }
}

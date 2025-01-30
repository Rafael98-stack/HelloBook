package project.HelloBook.Mappers;

import org.springframework.stereotype.Service;
import project.HelloBook.Dtos.AuthorDtos.AuthorRequestInsert;
import project.HelloBook.Dtos.AuthorDtos.AuthorRequestUpdate;
import project.HelloBook.Entities.Author;

@Service
public class AuthorMapper {

    public Author fromAuthorRequestInsert(AuthorRequestInsert request) {
        return Author
                .builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .nationality(request.nationality())
                .build();
    }

    public Author fromAuthorRequestUpdate(AuthorRequestUpdate request) {
        return Author
                .builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .nationality(request.nationality())
                .build();
    }
}

package project.HelloBook.Controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.HelloBook.Dtos.AuthorDtos.AuthorRequestInsert;
import project.HelloBook.Dtos.AuthorDtos.AuthorRequestUpdate;
import project.HelloBook.Dtos.AuthorDtos.AuthorResponse;
import project.HelloBook.Services.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/Author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @GetMapping("/get/{id}")
    public ResponseEntity<AuthorResponse> getById(@PathVariable Long id){
        return new ResponseEntity<>(authorService.getAuthorById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AuthorResponse>> getAll(){
        return  new ResponseEntity<>(authorService.getAllAuthors(),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<AuthorResponse> create(@RequestBody @Valid AuthorRequestInsert request){
        return new ResponseEntity<>(authorService.insertAuthor(request), HttpStatus.CREATED);
    }


    @PutMapping("/update")
public ResponseEntity<AuthorResponse> update( @PathVariable Long id,@RequestBody @Valid AuthorRequestUpdate request) {
        return new ResponseEntity<>(authorService.updateAuthorById(id,request), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AuthorResponse> deleteById(@PathVariable Long id){
        authorService.deleteAuthorById(id);
        return new ResponseEntity<>(
                new AuthorResponse(id),HttpStatus.OK);
    }
}

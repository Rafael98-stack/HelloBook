package project.HelloBook.Controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.HelloBook.Dtos.BookDtos.BookRequestInsert;
import project.HelloBook.Dtos.BookDtos.BookRequestUpdate;
import project.HelloBook.Dtos.BookDtos.BookResponse;
import project.HelloBook.Dtos.BookSelected;
import project.HelloBook.Services.BookService;

import java.util.List;

@RestController
@RequestMapping("/Book")
public class BookController {
    @Autowired
    BookService bookService;
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @GetMapping("/get/{id}")
    public ResponseEntity<BookResponse> getById(@PathVariable Long id){
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }

    @GetMapping("/get/{title}/{nationality}")
    public ResponseEntity<BookSelected> getByTitleAndNationality(@PathVariable @NotBlank String title, @PathVariable @NotBlank String nationality){
        logger.info("Searching for book with title: {} and nationality: {}", title, nationality);
        BookSelected bookResponse = bookService.getByTitleAndNationality(title, nationality);
        if (bookResponse != null) {
            return new ResponseEntity<>(bookResponse, HttpStatus.OK);
        } else {
            logger.error("Book not found with title: {} and nationality: {}", title, nationality);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookResponse>> getAll(){
        return  new ResponseEntity<>(bookService.getAllBooks(),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<BookResponse> create(@RequestBody @Valid BookRequestInsert request){
        return new ResponseEntity<>(bookService.insertBook(request), HttpStatus.CREATED);
    }


    @PutMapping("/update")
public ResponseEntity<BookResponse> update( @PathVariable Long id,@RequestBody @Valid BookRequestUpdate request) {
        return new ResponseEntity<>(bookService.updateBookById(id,request), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BookResponse> deleteById(@PathVariable Long id){
        bookService.deleteBookById(id);
        return new ResponseEntity<>(
                new BookResponse(id),HttpStatus.OK);
    }

    //ABC
}

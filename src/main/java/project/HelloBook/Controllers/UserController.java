package project.HelloBook.Controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.HelloBook.Dtos.UserDtos.UserRequestInsert;
import project.HelloBook.Dtos.UserDtos.UserRequestUpdate;
import project.HelloBook.Dtos.UserDtos.UserResponse;
import project.HelloBook.Services.UserService;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/get/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAll(){
        return  new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequestInsert request){
        return new ResponseEntity<>(userService.insertUser(request), HttpStatus.CREATED);
    }


    @PutMapping("/update")
public ResponseEntity<UserResponse> update( @PathVariable Long id,@RequestBody @Valid UserRequestUpdate request) {
        return new ResponseEntity<>(userService.updateUserById(id,request), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserResponse> deleteById(@PathVariable Long id){
        userService.deleteUserById(id);
        return new ResponseEntity<>(
                new UserResponse(id),HttpStatus.OK);
    }
}

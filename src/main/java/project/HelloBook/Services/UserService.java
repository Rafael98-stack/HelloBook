package project.HelloBook.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.HelloBook.Dtos.UserDtos.UserRequestInsert;
import project.HelloBook.Dtos.UserDtos.UserRequestUpdate;
import project.HelloBook.Dtos.UserDtos.UserResponse;
import project.HelloBook.Entities.User;
import project.HelloBook.ExceptionHandlers.Exceptions.AddressNotFoundException;
import project.HelloBook.ExceptionHandlers.Exceptions.UserNotFoundException;
import project.HelloBook.Mappers.UserMapper;
import project.HelloBook.Repositories.AddressDAO;
import project.HelloBook.Repositories.UserDAO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserDAO userDAO;
    private final AddressDAO addressDAO;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserDAO userDAO, AddressDAO addressDAO, UserMapper userMapper) {
        this.userDAO = userDAO;
        this.addressDAO = addressDAO;
        this.userMapper = userMapper;
    }


    public UserResponse insertUser(UserRequestInsert userRequestInsert){
        // Mapping method to "unpacking" current dto
        /*
        User user = userMapper.fromUserRequestInsert(userRequestInsert);

        return UserResponse
                .builder()
                .id(userDAO.save(user).getId())
                .build();
        */
        User user = new User(
        addressDAO.findById(userRequestInsert.id_address())
                .orElseThrow(() -> new AddressNotFoundException("Comune non trovato")),
                userRequestInsert.password(),
                userRequestInsert.email(),
                userRequestInsert.lastname(),
                userRequestInsert.firstname()
                );

        return UserResponse
                .builder()
                .id(userDAO.save(user).getId())
                .build();
    }

    public UserResponse updateUserById(Long id_user, UserRequestUpdate userRequestUpdate){
        User user = userDAO.findById(id_user)
                .orElseThrow(()-> new UserNotFoundException("User con id " + id_user + " non trovato"));

        user.setEmail(userRequestUpdate.email());
        user.setFirstname(userRequestUpdate.firstname());
        user.setLastname(userRequestUpdate.lastname());
        user.setPassword(userRequestUpdate.password());
        user.setAddress(addressDAO.findById(userRequestUpdate.id_address())
                .orElseThrow(()-> new AddressNotFoundException("Address con id " + userRequestUpdate.id_address() + " non trovato")));

        return UserResponse
                .builder()
                .id(userDAO.save(user).getId())
                .build();
    }

    public UserResponse getUserById(Long id_user){
        return UserResponse
                .builder()
                .id(userDAO.findById(id_user )
                        .orElseThrow(()-> new UserNotFoundException("User con id " + id_user + " non trovato")).getId())
                .build();
    }

    public List<UserResponse> getAllUsers(){
        List<User> users = userDAO.findAll();

        return users.stream()
                .map(user -> new UserResponse(user.getId()))
                .collect(Collectors.toList());

    }

    public void deleteUserById(Long id_user){
        userDAO.deleteById(id_user);
    }
}

package project.HelloBook.Mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.HelloBook.Dtos.UserDtos.UserRequestInsert;
import project.HelloBook.Entities.User;
import project.HelloBook.ExceptionHandlers.Exceptions.AddressNotFoundException;
import project.HelloBook.Repositories.AddressDAO;

@Service
public class UserMapper {

    @Autowired
    private AddressDAO addressDAO;

    public User fromUserRequestInsert(UserRequestInsert request) {
        return User
                .builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .password(request.password())
                .address(addressDAO.findById(request.id_address())
                        .orElseThrow(()-> new AddressNotFoundException("Address con non trovato")))
                .build();
    }


}

package project.HelloBook.Mappers;

import org.springframework.stereotype.Service;
import project.HelloBook.Dtos.AddressDtos.AddressRequestInsert;
import project.HelloBook.Entities.Address;

@Service
public class AddressMapper {

    public Address fromAddressRequestInsert(AddressRequestInsert request) {
        return Address
                .builder()
                .street(request.street())
                .city(request.city())
                .zip_code(request.zip_code())
                .build();
    }
}

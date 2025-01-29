package project.HelloBook.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.HelloBook.Dtos.AddressDtos.AddressRequestInsert;
import project.HelloBook.Dtos.AddressDtos.AddressRequestUpdate;
import project.HelloBook.Dtos.AddressDtos.AddressResponse;
import project.HelloBook.Entities.Address;
import project.HelloBook.ExceptionHandlers.Exceptions.AddressNotFoundException;
import project.HelloBook.Repositories.AddressDAO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    private final AddressDAO addressDAO;
    @Autowired
    public AddressService(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    public AddressResponse insertAddress(AddressRequestInsert addressRequestInsert){

        Address address = new Address(
                addressRequestInsert.street(),
                addressRequestInsert.city(),
                addressRequestInsert.zip_code()
        );

        return AddressResponse
                .builder()
                .id(addressDAO.save(address).getId())
                .build();
    }

    public AddressResponse updateAddressById(Long id_address, AddressRequestUpdate addressRequestUpdate){
        Address address = addressDAO.findById(id_address)
                .orElseThrow(()-> new AddressNotFoundException("Address con id " + id_address + " non trovato"));

        address.setCity(addressRequestUpdate.city());
        address.setStreet(addressRequestUpdate.street());
        address.setZip_code(addressRequestUpdate.zip_code());

        return AddressResponse
                .builder()
                .id(addressDAO.save(address).getId())
                .build();
    }

    public AddressResponse getAddressById(Long id_address){
        return AddressResponse
                .builder()
                .id(addressDAO.findById(id_address )
                        .orElseThrow(()-> new AddressNotFoundException("Address con id " + id_address + " non trovato")).getId())
                .build();
    }

    public List<AddressResponse> getAllAddresss(){
        List<Address> addresss = addressDAO.findAll();

        return addresss.stream()
                .map(address -> new AddressResponse(address.getId()))
                .collect(Collectors.toList());

    }

    public void deleteAddressById(Long id_address){
        addressDAO.deleteById(id_address);
    }
}

package project.HelloBook.Controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.HelloBook.Dtos.AddressDtos.AddressRequestInsert;
import project.HelloBook.Dtos.AddressDtos.AddressRequestUpdate;
import project.HelloBook.Dtos.AddressDtos.AddressResponse;
import project.HelloBook.Services.AddressService;

import java.util.List;

@RestController
@RequestMapping("/Address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @GetMapping("/get/{id}")
    public ResponseEntity<AddressResponse> getById(@PathVariable Long id){
        return new ResponseEntity<>(addressService.getAddressById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AddressResponse>> getAll(){
        return  new ResponseEntity<>(addressService.getAllAddresss(),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<AddressResponse> create(@RequestBody @Valid AddressRequestInsert request){
        return new ResponseEntity<>(addressService.insertAddress(request), HttpStatus.CREATED);
    }


    @PutMapping("/update")
public ResponseEntity<AddressResponse> update( @PathVariable Long id,@RequestBody @Valid AddressRequestUpdate request) {
        return new ResponseEntity<>(addressService.updateAddressById(id,request), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AddressResponse> deleteById(@PathVariable Long id){
        addressService.deleteAddressById(id);
        return new ResponseEntity<>(
                new AddressResponse(id),HttpStatus.OK);
    }
}

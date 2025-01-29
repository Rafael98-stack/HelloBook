package project.HelloBook.Dtos.AddressDtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AddressRequestUpdate(
        @NotBlank(message = "Inserire la via")
        String street,
        @NotBlank(message = "Inserire la citta")
        String city,
        @Email(message = "Inserire lo zip_code")
        String zip_code
) {
}

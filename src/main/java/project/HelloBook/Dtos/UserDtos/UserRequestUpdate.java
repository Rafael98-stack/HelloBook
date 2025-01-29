package project.HelloBook.Dtos.UserDtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequestUpdate(
        @NotBlank(message = "Inserire il nome")
        String firstname,
        @NotBlank(message = "Inserire il cognome")
        String lastname,
        @Email(message = "Inserire la l'email")
        String email,
        @NotNull(message = "Inserire una password")
        String password,
        @NotNull(message = "Inserire una via")
        Long id_address
) {
}

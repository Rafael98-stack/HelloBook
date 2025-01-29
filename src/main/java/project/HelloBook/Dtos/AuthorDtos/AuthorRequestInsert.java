package project.HelloBook.Dtos.AuthorDtos;

import jakarta.validation.constraints.NotBlank;

public record AuthorRequestInsert(
        @NotBlank(message = "Inserire il nome")
        String firstname,
        @NotBlank(message = "Inserire il cognome")
        String lastname,
        @NotBlank(message = "Inserire la nazionalita")
        String nationality
) {
}

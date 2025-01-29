package project.HelloBook.Dtos.BookDtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BookRequestInsert(
        @NotBlank(message = "Inserire il titolo")
        String title,
        @NotBlank(message = "Inserire il prezzo")
        Double price,
        @Future(message = "Inserire la data")
        LocalDate published_date,
        @NotNull(message = "Inserire una quantità per lo stock")
        Integer stock,
        @NotNull(message = "Inserire l'id_author")
        Long id_author
) {
}

package project.HelloBook.Dtos;

import lombok.Builder;

import java.time.LocalDate;
@Builder
public record BookSelected(
        Long id,
        String title,
        Double price,
        LocalDate publishedDate,
        Integer stock,
        String firstname,
        String lastname,
        String nationality
) {
}

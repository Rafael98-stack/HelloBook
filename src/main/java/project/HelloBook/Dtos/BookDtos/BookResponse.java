package project.HelloBook.Dtos.BookDtos;

import lombok.Builder;

@Builder
public record BookResponse(
        Long id
) {
}

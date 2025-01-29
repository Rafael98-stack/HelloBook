package project.HelloBook.Dtos.AuthorDtos;

import lombok.Builder;

@Builder
public record AuthorResponse(
        Long id
) {
}

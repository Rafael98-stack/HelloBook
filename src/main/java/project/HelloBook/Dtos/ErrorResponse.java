package project.HelloBook.Dtos;

import lombok.Builder;

@Builder
public record ErrorResponse(
        String exception,
        String message
) {
}

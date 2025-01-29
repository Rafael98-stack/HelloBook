package project.HelloBook.Dtos.UserDtos;

import lombok.Builder;

@Builder
public record UserResponse(
        Long id
) {
}

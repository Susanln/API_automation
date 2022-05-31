package dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class AuthResponseDto {
    String token;
}

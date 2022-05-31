package dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class AuthRequestDto {
    String email;
    String password;

}

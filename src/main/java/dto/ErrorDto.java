package dto;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ErrorDto {
    String message;
    String details;
    int code;
}

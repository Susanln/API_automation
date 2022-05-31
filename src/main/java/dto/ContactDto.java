package dto;

import lombok.*;

@Builder
@Setter
@Getter
@ToString
@AllArgsConstructor
public class ContactDto {
    String address;
    String description;
    String email;
    String lastName;
    String name;
    String phone;
    int id;
}

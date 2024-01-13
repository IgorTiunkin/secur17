package com.example.secur17.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PersonDTO {

    @NotEmpty(message = "Username should not be empty")
    @Size(min = 2, max = 100, message = "size from 2 to 100")
    private String username;

    @NotEmpty(message = "Password should not be empty")
    @Size (min = 2, max = 100, message = "size from 2 to 100")
    private String password;

    private String role;

    public PersonDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

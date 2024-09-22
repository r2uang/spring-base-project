package com.msa.springbaseproject.business.user.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String userName;
    private String email;
    private String password;

    @NotBlank(message = "First name is not blank")
    private String firstName;

    @NotBlank(message = "Last name is not blank")
    private String lastName;

}

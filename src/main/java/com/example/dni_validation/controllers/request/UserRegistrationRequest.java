package com.example.dni_validation.controllers.request;

import com.example.dni_validation.utils.annotations.ValidSpanishDocument;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequest implements Serializable {

    @JsonProperty("full_name")
    @NotBlank(message = "Full name is required")
    private String fullName;

    @JsonProperty("email")
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be in a valid format")
    private String email;

    @JsonProperty("document")
    @ValidSpanishDocument(message = "Document must be a valid DNI or NIE")
    private String document;

}

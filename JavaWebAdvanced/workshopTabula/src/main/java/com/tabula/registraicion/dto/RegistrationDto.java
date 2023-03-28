package com.tabula.registraicion.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tabula.users.RoleEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldMatch(first = "password",second = "repeatPassword", message = "The passwords do not match!")
public class RegistrationDto {

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String repeatPassword;


}

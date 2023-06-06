package com.works.entities.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CustomerLoginDto {

    @Size(min = 7, max = 200)
    @Email
    @NotEmpty
    @NotNull
    @Column(unique = true, length = 200)
    private String email;

    @Size(min = 5)
    @NotEmpty
    @NotNull
    @Column(length = 500)
    private String password;

}

package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    @Size(min = 1, max = 100)
    @NotEmpty
    @NotNull
    @Column(length = 100)
    private String name;

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

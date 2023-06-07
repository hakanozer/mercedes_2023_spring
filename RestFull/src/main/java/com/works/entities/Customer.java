package com.works.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class Customer extends Base {

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

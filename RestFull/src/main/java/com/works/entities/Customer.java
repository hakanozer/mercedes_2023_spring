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

    @PostLoad
    public void postLoad() {
        System.out.println("postLoad Call");
    }

    @PrePersist
    public void prePersist() {
        System.out.println("prePersist Call -1");
    }

    @PostPersist
    public void postPersist() {
        System.out.println("postPersist Call -2");
    }


    @PreRemove
    public void preRemove() {
        System.out.println("preRemove Call -1");
    }

    @PostRemove
    public void postRemove() {
        System.out.println("postRemove Call -2");
    }


    @PreUpdate
    public void preUpdate() {
        System.out.println("preUpdate Call -1");
    }

    @PostUpdate
    public void postUpdate() {
        System.out.println("postUpdate Call -2");
    }
}

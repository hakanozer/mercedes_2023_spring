package com.works.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public class Base {

    @CreatedDate
    @JsonIgnore
    private Long createdDate;

    @CreatedBy
    @JsonIgnore
    private String createdBy;

    @LastModifiedDate
    @JsonIgnore
    private Long lastModifiedDate;

    @LastModifiedBy
    @JsonIgnore
    private String lastModifiedBy;


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

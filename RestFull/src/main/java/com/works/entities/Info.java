package com.works.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Info {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iid;

    private String url;
    private String name;
    private String roles;
    private String sessionID;
    private Long date;

    public Info() {
    }

    public Info(String url, String name, String roles, String sessionID, Long date) {
        this.url = url;
        this.name = name;
        this.roles = roles;
        this.sessionID = sessionID;
        this.date = date;
    }
}

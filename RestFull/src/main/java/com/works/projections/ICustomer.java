package com.works.projections;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public interface ICustomer {

    Long getCId();
    String getName();
    @JsonProperty("email")
    String getEmaIl();
    @JsonIgnore
    String getPassword();

}

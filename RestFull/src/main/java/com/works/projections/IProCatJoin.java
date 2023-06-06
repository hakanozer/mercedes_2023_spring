package com.works.projections;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public interface IProCatJoin {

    Long getPId();
    // @JsonIgnore
    Long getCId();
    String getTItle();
    @JsonProperty("detail")
    String getDetaIl();
    String getName();


}

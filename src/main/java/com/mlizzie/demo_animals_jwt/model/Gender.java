package com.mlizzie.demo_animals_jwt.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum  Gender {
     @JsonProperty("male")
     MALE,
    @JsonProperty("female")
     FEMALE
}
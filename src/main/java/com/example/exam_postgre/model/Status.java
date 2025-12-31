package com.example.exam_postgre.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Status {
    PENDING, READY, LOADED, DELIVERED;

    @JsonCreator
    public static Status fromString(String value) {
        if (value == null) {
            return null;
        }
        return Status.valueOf(value.toUpperCase());
    }
}

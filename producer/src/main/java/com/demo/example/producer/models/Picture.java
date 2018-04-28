package com.demo.example.producer.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Picture implements Serializable {
    private final Long id;
    private final String name;
    private final String imgUrl;

    @JsonCreator
    public Picture(@JsonProperty("id") Long id,
                   @JsonProperty("name") String name,
                   @JsonProperty("imageUrl") String imgUrl) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}

package com.demo.example.consumer.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.sql.Date;

public class Picture implements Serializable {
    private final Long id;
    private final String name;
    private final String imgUrl;
    private final Date createdAt;

    @JsonCreator
    public Picture(@JsonProperty("id") Long id,
                   @JsonProperty("name") String name,
                   @JsonProperty("imageUrl") String imgUrl,
                   @JsonProperty("date") Date createdAt) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.createdAt = createdAt;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

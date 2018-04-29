package com.demo.example.producer.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "picture", schema = "public")
public class Picture implements Serializable {
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String imgUrl;

    @Column(name = "date_created")
    private Date createdAt;

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

    public Picture() {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Picture picture = (Picture) o;
        return Objects.equals(id, picture.id) &&
                Objects.equals(name, picture.name) &&
                Objects.equals(imgUrl, picture.imgUrl) &&
                Objects.equals(createdAt, picture.createdAt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, imgUrl, createdAt);
    }
}

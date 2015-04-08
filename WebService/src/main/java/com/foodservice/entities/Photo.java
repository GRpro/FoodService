package com.foodservice.entities;

import com.foodservice.entities.data.LazyClonable;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@javax.persistence.Table(name = "photo")
public class Photo implements LazyClonable<Photo> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Lob
    private byte[] image;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        if (id != null ? !id.equals(photo.id) : photo.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", image=" + Arrays.toString(image) +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public Photo clone() {
        return this;
    }
}

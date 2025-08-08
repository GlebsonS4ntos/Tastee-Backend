package com.Glebson.Tastee.Domain;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity()
public class Image implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String urlImg;
    private String imgIdCloudnary;
    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Image() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getImgIdCloudnary() {
        return imgIdCloudnary;
    }

    public void setImgIdCloudnary(String imgIdCloudnary) {
        this.imgIdCloudnary = imgIdCloudnary;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Image image)) return false;
        return Objects.equals(getId(), image.getId()) && Objects.equals(getUrlImg(), image.getUrlImg());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUrlImg());
    }
}

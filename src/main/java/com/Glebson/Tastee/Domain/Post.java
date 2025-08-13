package com.Glebson.Tastee.Domain;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Post implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String title;
    @Column(length = 2000)
    private String content;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "post_category",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;
    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Image image;

    public Post() {
    }

    public void clearCategories(){
        this.categories = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getUrlImg() {
        if (image != null && image.getUrlImg() != null) {
            return image.getUrlImg();
        }
        return "";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Post post)) return false;
        return Objects.equals(getId(), post.getId()) && Objects.equals(getTitle(), post.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle());
    }
}

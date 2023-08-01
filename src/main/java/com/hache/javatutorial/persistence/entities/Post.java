package com.hache.javatutorial.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "posts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq")
    @SequenceGenerator(name = "post_seq", sequenceName = "post_seq", allocationSize = 1)
    private Long postId;

    private String title;
    private String msgPost;

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private PostDetail details;

    @Builder.Default // Lombok inicializa la lista.
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PostComment> comments = new HashSet<>();

    @Builder.Default // Lombok inicializa la lista.
    // En una relacion @ManyToMany no debemos agregar CascadeType.REMOVE en la cascada porque puede generar errores.
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "post_tag", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

    //Por la relacion OneToOne hay que definir el set bidireccional
    public void setDetails(final PostDetail details) {
        if (details == null) {
            if (this.details != null) {
                this.details.setPost(null);
            }
        } else {
            details.setPost(this);
        }
        this.details = details;
    }

    public void addComment(final PostComment comment) {
        comments.add(comment);
        comment.setPost(this);
    }

    public void removeComment(final PostComment comment) {
        comments.remove(comment);
        comment.setPost(null);
    }

    public void addTag(final Tag tag) {
        tags.add(tag);
        tag.getPost().add(this);
    }

    public void removeTag(final Tag tag) {
        tags.remove(tag);
        tag.getPost().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post post)) return false;
        return Objects.equals(postId, post.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId);
    }
}

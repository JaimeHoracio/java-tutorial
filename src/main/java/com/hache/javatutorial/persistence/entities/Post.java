package com.hache.javatutorial.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;
    private String title;
    private String msgPost;

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private PostDetails details;

    @Builder.Default // Lombok inicializa la lista.
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostComments> comments = new ArrayList<>();

    public void addComments(final PostComments comment) {
        comments.add(comment);
        comment.setPost(this);
    }

    public void removeComments(final PostComments comment) {
        comments.remove(comment);
        comment.setPost(null);
    }

}

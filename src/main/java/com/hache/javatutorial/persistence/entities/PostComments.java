package com.hache.javatutorial.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "post_commets")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostComments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPostComment;

    private String review;

    /**
     * Agregar FetchType.LAZY ya que por defecto es EAGER
     * Para que sea bidireccional agregar tambien @ManyToOne
     * Al ser bidireccional se evita tener una tabla auxiliar para el mapeo de la coleccion.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_post")
    private Post post;

    /**
     * Es recomendable sobrescribir equals y hashCode en caso de que haya
     * alguna particularidad en identificar un comentario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostComments that)) return false;
        return Objects.equals(idPostComment, that.idPostComment) &&
                Objects.equals(review, that.review) &&
                Objects.equals(post, that.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPostComment, review, post);
    }
}

package com.hache.javatutorial.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "commets")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq")
    @SequenceGenerator(name = "comment_seq", sequenceName = "comment_seq", allocationSize = 1)
    private Long commentId;

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
        if (!(o instanceof PostComment that)) return false;
        //Con el identificador es suficiente para identificar el comentario.
        return Objects.equals(commentId, that.commentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId);
    }
}

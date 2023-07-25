package com.hache.javatutorial.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post_details")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDetails {
    @Id
    private Long idPostDetails;
    private String detailsPost;

    /**
     * Agregando @MapsId en la entidad secundaria y @JoinColumn con el nombre de la PK de la entidad primaria
     * permite optimizar las intrucciones SQL que Hibernate usa para sincronizar la base de datos.
     * A su vez utiliza el valor del PK y FK en la misma columna por lo que no es necesario una estrategia
     * en el identificador de la entidad secundaria.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @MapsId
    private Post post;

}

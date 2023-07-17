package com.hache.javatutorial.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

    private String titulo;
    private float duracion; //Expresada en horas
    private int videos; //cantidad de vídeos
    private int alumnos; //Cantidad de alumnos
}

package com.hache.javatutorial.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Genera Getter y Setter de todos los atributos de la clase
@Builder //Crea el patron Builder y no tener que usar new()
@NoArgsConstructor
@AllArgsConstructor
public class Persona {

    private String id;
    private String name;
    private Integer age;

}

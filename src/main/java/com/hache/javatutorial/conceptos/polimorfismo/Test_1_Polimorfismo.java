package com.hache.javatutorial.conceptos.polimorfismo;

import com.hache.javatutorial.conceptos.claseAbstract.Animal;
import com.hache.javatutorial.conceptos.claseAbstract.Pajaro;
import com.hache.javatutorial.conceptos.claseAbstract.Vaca;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class Test_1_Polimorfismo {

    public static void main(String[] args) {
        // Mecanismo que permite dar diferente comportamiento para distintas clases a un metodo con el mismo nombre.

        //Gracias al Polimorfismo puedo agregar una lista e invocar un metodo para que den diferentes mensajes.
        Animal vaca = new Vaca();
        Animal pajaro = new Pajaro();

        List<Animal> list = Arrays.asList(vaca, pajaro);

        // Aplicamos Polimorfismo al invocar el metodo comer() pero desde distintas clases hijas.
        list.stream().forEach(a -> a.comer());

        // Con Java 17 podemos preguntar e instanciar en la misma linea
        if(pajaro instanceof Pajaro pa) {
            pa.volar();
        }

    }
}

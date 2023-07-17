package com.hache.javatutorial.conceptos.streams;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Slf4j
public class ForEachTest {

    //Defino una arrow function
    static Consumer imprimir = c -> log.info(String.valueOf(c));

    public static void main(String[] args) {

        //Genero los caracteres del abecedario
        List<Character> list = new ArrayList<>();
        for(char c = 'A'; c < 'Z'; ++c){
            list.add(c);
        }

        // El forEach utiliza la interfaz iterator de collection.

        // Resultado en orden de insercion
        list.forEach(imprimir);

        log.info("  **************  ");

        // Resultado en orden de insercion
        list.stream().forEach(imprimir);

        log.info("  **************  ");

        // NO se garantiza en el orden de insercion.
        // El forEach de un stream paralelo dependerá que caracter tome cada hilo.
        list.parallelStream().forEach(imprimir);

    }

}

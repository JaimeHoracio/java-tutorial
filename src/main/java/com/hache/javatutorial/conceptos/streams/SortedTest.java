package com.hache.javatutorial.conceptos.streams;

import com.hache.javatutorial.dto.Persona;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SortedTest {

    public static void main(String[] args) {

        List<Integer> listNeg = Arrays.asList(-3, -12, -5, -9);
        List<Integer> listPos = Arrays.asList(23, 45, 0, 2, 32, 21);

        // Hay que convertir los elementos primitivos a objetos.
        List<Integer> listNum = Stream.of(listNeg, listPos).flatMap(Collection::stream).collect(Collectors.toList());

        //Orden ASCENDENTE (por defecto)
        listNum.stream().sorted().forEach(n -> System.out.print(n + " "));
        System.out.println();

        // DESCENDENTE
        listNum.stream().sorted(Comparator.reverseOrder()).forEach(n -> System.out.print(n + " "));
        System.out.println();

        //Lista String
        List<String> listStr = Arrays.asList("9", "A", "Z", "1", "B", "Y", "4", "a", "c");
        //
        listStr.stream().sorted().forEach(n -> System.out.print(n + " "));
        System.out.println();

        //Ordenamos a las personas por edad
        List<Persona> users = Arrays.asList(
                Persona.builder().name("C").age(30).build(),
                Persona.builder().name("D").age(40).build(),
                Persona.builder().name("A").age(10).build(),
                Persona.builder().name("B").age(20).build(),
                Persona.builder().name("C").age(50).build());

        users.stream().sorted(Comparator.comparingInt(Persona::getAge)).forEach(n -> System.out.print(n + " "));
        System.out.println();

    }

}

package com.hache.javatutorial.conceptos.streams;

import com.hache.javatutorial.dto.Curso;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@Slf4j
public class FilterReduceMaxTest {

    static List<Curso> cursos = new ArrayList<>();

    public static void main(String[] arg) {
        initList();

        // Obtener la cantidad de cursos con una duración mayor a 5 horas.
        log.info("Obtener la cantidad de cursos con una duración mayor a 5 horas");
        cursosDuracionMayor5Horas();

        // Listar el título de todos aquellos cursos con una cantidad de vídeos mayor a 50.
        log.info("Listar el título de todos aquellos cursos con una cantidad de vídeos mayor a 50");
        titulosCursosVideoMayor50();

        // Mostrar en consola la duración total de todos los cursos.
        log.info("Mostrar en consola la duración total de todos los cursos.");
        duracionTotalCursos();

        // Mostrar en consola todos aquellos cursos que superen el promedio en cuanto a duración se refiere.
        log.info("Mostrar en consola todos aquellos cursos que superen el promedio en cuanto a duración se refiere.");
        cursosDuracionMayorPromedio();

        // Obtener el curso con mayor duración.
        log.info("Obtener el curso con mayor duración");
        cursoMayorDuracion();

        // Obtener el curso con el titulo mas largo.
        log.info("Obtener el curso con el titulo mas largo");
        cursoTituloMasLargo();
    }

    static void initList() {
        cursos.add(new Curso("Cursos profesional de Java", 6.5f, 50, 200));
        cursos.add(new Curso("Cursos profesional de Python", 8.5f, 60, 800));
        cursos.add(new Curso("Cursos profesional de DB", 4.5f, 70, 700));
        cursos.add(new Curso("Cursos profesional de Android", 7.5f, 10, 400));
        cursos.add(new Curso("Cursos profesional de Escritura", 1.5f, 10, 300));
    }

    static void cursosDuracionMayor5Horas() {
        cursos.stream().filter(c -> c.getDuracion() > 5f)
                .forEach(System.out::println);
    }

    static void titulosCursosVideoMayor50() {
        cursos.stream().filter(c -> c.getVideos() > 50)
                .map(c -> c.getTitulo())
                .forEach(System.out::println);
    }

    static void duracionTotalCursos() {
        System.out.println(cursos.stream()
                        .map(c -> c.getDuracion())
                        .reduce(0f, (a, b) -> a + b) //Agregando valor inicial, reduce se convierte en final.
                //es lo mismo que hacer
                //.reduce(0, Float::sum)
        );
    }

    static void cursosDuracionMayorPromedio() {
        /* Una opcion
        Optional<Float> promedio = cursos.stream().map(c -> c.getDuracion())
                .reduce(Float::sum)
                .map(sumDuration -> sumDuration / cursos.size());
        */

        //Opcion mas corta
        OptionalDouble promedio = cursos.stream().mapToDouble(c -> c.getDuracion()).average();

        cursos.stream().filter(c -> c.getDuracion() > promedio.getAsDouble())
                .forEach(System.out::println);
    }

    static void cursoMayorDuracion() {
        /* Una opcion
        Float maximo = Collections.max(cursos.stream().map(c -> c.getDuracion()).collect(Collectors.toList()));
        cursos.stream().filter(c -> c.getDuracion() == maximo)
                .forEach(System.out::println);
        */

        //Mas corta
        System.out.println(cursos.stream().reduce((a, b) -> a.getDuracion() > b.getDuracion() ? a : b).get());
    }

    static void cursoTituloMasLargo() {
        System.out.println(cursos.stream()
                .reduce((a, b) -> a.getTitulo().length() > b.getTitulo().length() ? a : b).get());
    }

}

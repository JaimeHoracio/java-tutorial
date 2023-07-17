package com.hache.javatutorial.conceptos.claseAbstract;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Pajaro extends Animal{

    //Se debe implementar metodo heredado abstracto
    //NO es obligatorio agregar anotacion @Override
    public void comer() {
        log.info("Como semillas!");
    }

    // Se puede agregar otros metodos
    public void volar() {
        log.info("Soy Pajaro y puedo volar!");
    }

    //Las hijas pueden heredar atributos public y protected de los padres.
    // Se accede prefijo super directamente del padre
    // o con this para que busque primero en la clase actual y luego en el padre.
    public void queSoy() {
        log.info("Soy un Pajaro y un {}", this.queSoy);
    }
}

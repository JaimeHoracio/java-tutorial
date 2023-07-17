package com.hache.javatutorial.conceptos.claseAbstract;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Vaca extends Animal {

    //Debo implementar metodo abstacto heredado
    //Para metodos abstractos se puede o no poner la anotacion @Override
    @Override
    public void comer() {
        log.info("Como pasto!");
    }

    // Esta clase ya esta definida en el padre pero la sobrescribo
    // No es necesario agregar @Override
    public void saludar() {
        log.info("Muuuuu!");
    }

}

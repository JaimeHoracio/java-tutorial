package com.hache.javatutorial.conceptos.claseAbstract;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Animal {
    //Clase abstracta no debe instanciarse

    protected String queSoy= "Soy un animal";

    //Metodo abstracto, hijas deben implementar
    public abstract void comer();

    //Metodo publico se hereda a las hijas.
    public void dormir(){
        log.info(getMessage());
    }

    //Solo accedida por la clse Animal y sus hijas
    protected void saludar() {
        log.info("Hola desde Animal");
    }

    //Metodo privado no es accedido por otras clases.
    private String getMessage() {
        return "Estoy durmiendo!";
    }

}

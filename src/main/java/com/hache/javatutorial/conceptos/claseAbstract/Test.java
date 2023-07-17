package com.hache.javatutorial.conceptos.claseAbstract;

public class Test {

    public static void main(String[] args) {
        Animal vaca = new Vaca();
        vaca.comer(); //Como pasto!
        vaca.dormir(); //Estoy durmiendo!

        Animal pajaro = new Pajaro();
        pajaro.comer(); //Como semillas!
        pajaro.dormir(); //Estoy durmiendo!

        //No puedo usar el metodo volar porque no esta en Animal y pajaro es de tipo animal
        //pajaro.volar();

        //Debe ser tipo Pajaro para acceder a metodos solo de Pajaro.
        Pajaro pajaroVolar = new Pajaro();
        pajaroVolar.volar(); //Puedo volar!
        pajaroVolar.queSoy();

        vaca.saludar(); //Muuuuu!
        pajaro.saludar(); // Hola desde Animail
    }
}

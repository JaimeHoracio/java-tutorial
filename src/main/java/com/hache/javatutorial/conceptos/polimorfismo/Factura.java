package com.hache.javatutorial.conceptos.polimorfismo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Factura {
    protected String referencia;
    protected Double monto;

    public void facturar() {
        System.out.println(this.referencia + " - monto: " + this.monto);
    }

    public static void imprimir(){
        System.out.println("Impresion de Factura común");
    }
}

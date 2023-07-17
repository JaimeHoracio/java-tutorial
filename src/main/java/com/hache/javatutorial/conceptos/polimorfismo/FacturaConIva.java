package com.hache.javatutorial.conceptos.polimorfismo;

public class FacturaConIva extends Factura {

    private Double porcentajeIVA;

    public FacturaConIva(String referencia, Double monto) {
        super(referencia, monto);
        porcentajeIVA = 1.22;
    }

    public void facturar() {
        System.out.println(this.referencia + " - monto con IVA: " + this.monto * porcentajeIVA);
    }

    public static void imprimir() {
        System.out.println("Impresion de Factura con IVA");
    }

}

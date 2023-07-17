package com.hache.javatutorial.conceptos.polimorfismo;

public class Test_2_Polimorfismo {

    public static void main(String[] args) {

        /**** POLIMORFISMO ******/
        Factura f = new FacturaConIva("Factura 1", 100d);
        f.facturar(); //Factura 1 - monto con IVA: 122.0 (Mensaje Hijo)
        /************************/

        /**** Anulacion metodo static ******/
        // No se puede anular un metodo static declarado en el padre.
        f.imprimir(); // Impresion de Factura común (Mensaje Padre)

        FacturaConIva fIva = new FacturaConIva("Factura 2", 100d);
        fIva.imprimir(); // Impresion de Factura con IVA (Mensaje Hijo)
        /************************/
    }
}

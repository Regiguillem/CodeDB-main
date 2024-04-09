package controlador;

import modelo.FacturaModelo;
import vista.FacturaVista;

public class FacturaControlador {
    //Atributos

    FacturaModelo modeloFac;
    FacturaVista vistaFac;

    //Contructor

    public FacturaControlador(FacturaModelo modeloFac, FacturaVista vistaFac) {
        this.modeloFac = modeloFac;
        this.vistaFac = vistaFac;
    }

    //Metodos

    void generar_factura(){}
}

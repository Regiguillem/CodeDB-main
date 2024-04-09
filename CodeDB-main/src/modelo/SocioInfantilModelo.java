package modelo;

import modelo.SociosModelo;

public class SocioInfantilModelo extends SociosModelo {
    //Atributos
    SociosModelo n_socioPadreMadre;
    int descuento_cuota;

    //Constructor

    public SocioInfantilModelo(String nombre, SociosModelo n_socioPadreMadre) {
        super(nombre);
        this.n_socioPadreMadre = n_socioPadreMadre;
        this.descuento_cuota = 50;
    }


    //Getters y Setters

    public SociosModelo getN_socioPadreMadre() {
        return n_socioPadreMadre;
    }

    public void setN_socioPadreMadre(SociosModelo n_socioPadreMadre) {
        this.n_socioPadreMadre = n_socioPadreMadre;
    }

    public int getDescuento_cuota() {
        return descuento_cuota;
    }

    public void setDescuento_cuota(int descuento_cuota) {
        this.descuento_cuota = descuento_cuota;
    }
}

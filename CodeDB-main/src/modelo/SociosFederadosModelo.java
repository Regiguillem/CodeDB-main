package modelo;

import modelo.SociosModelo;

public class SociosFederadosModelo extends SociosModelo {
    //Atributos
    String nif;
    FederacionesModelo federacion;
    int descuento_cuota;
    int descuento_exc;

    //Constructor

    public SociosFederadosModelo(int n_socio, String nombre, String nif, FederacionesModelo federacion) {
        super(n_socio, nombre);
        this.nif = nif;
        this.federacion = federacion;
        this.descuento_cuota = 5;
        this.descuento_exc = 10;
    }


    //Getters y Setters

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public FederacionesModelo getFederacion() {
        return federacion;
    }

    public void setFederacion(FederacionesModelo federacion) {
        this.federacion = federacion;
    }

    public int getDescuento_cuota() {
        return descuento_cuota;
    }

    public void setDescuento_cuota(int descuento_cuota) {
        this.descuento_cuota = descuento_cuota;
    }

    public int getDescuento_exc() {
        return descuento_exc;
    }

    public void setDescuento_exc(int descuento_exc) {
        this.descuento_exc = descuento_exc;
    }
}

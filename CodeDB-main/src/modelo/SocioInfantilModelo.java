package modelo;
public class SocioInfantilModelo extends SociosModelo {
    //Atributos
    private SociosModelo n_socioPadreMadre;
    private double descuento_cuota;

    //Constructor

    public SocioInfantilModelo(String nombre, SociosModelo n_socioPadreMadre) {
        super(nombre);
        this.n_socioPadreMadre = n_socioPadreMadre;
        this.descuento_cuota = 0.5;
    }


    //Getters y Setters

    public SociosModelo getN_socioPadreMadre() {
        return n_socioPadreMadre;
    }

    public void setN_socioPadreMadre(SociosModelo n_socioPadreMadre) {
        this.n_socioPadreMadre = n_socioPadreMadre;
    }

    public double getDescuento_cuota() {
        return descuento_cuota;
    }

    public void setDescuento_cuota(double descuento_cuota) {
        this.descuento_cuota = descuento_cuota;
    }
    public String tipoSocio() {
        return "Infantil";
    }
}

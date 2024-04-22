package modelo;

public class SocioEstandarModelo extends SociosModelo {
    //Atributos
    private String nif;
    private SeguroModelo seguro;

    //Constructor

    public SocioEstandarModelo(String nombre, String nif, SeguroModelo seguro) {
        super(nombre);
        this.nif = nif;
        this.seguro = seguro;
    }


    //Getters y Setters

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public SeguroModelo getSeguro() {
        return seguro;
    }

    public void setSeguro(SeguroModelo seguro) {
        this.seguro = seguro;
    }
    public String tipoSocio() {
        return "Estandar";
    }
}

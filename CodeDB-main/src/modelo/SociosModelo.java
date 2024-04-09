package modelo;

public class SociosModelo {
    //Atributos
    private static int ultimoNumeroSocio = 1;
    private int n_socio;
    private String nombre;
    private SeguroModelo seguro;

    //Constructor

    public SociosModelo(String nombre) {
        this.n_socio = ultimoNumeroSocio++;
        this.nombre = nombre;
    }

    //Getters y Setters

    public int getN_socio() {
        return n_socio;
    }

    public void setN_socio(int n_socio) {
        this.n_socio = n_socio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public SeguroModelo getSeguro() {
        return seguro;
    }

    public void setSeguro(SeguroModelo seguro) {
        this.seguro = seguro;
    }
}

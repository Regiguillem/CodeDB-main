package modelo;

public abstract class SociosModelo {
    //Atributos
    private static int ultimoNumeroSocio = 1;
    private int n_socio;
    private String nombre;
    private SeguroModelo seguro;

    private double cuotaMensual;

    //Constructor

    public SociosModelo(String nombre) {
        this.n_socio = ultimoNumeroSocio++;
        this.nombre = nombre;
        this.cuotaMensual = 50;
    }

    //Getters y Setters

    public String getN_socio() {
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

    public double getCuotaMensual() {
        return cuotaMensual;
    }
    public abstract String tipoSocio();

    public void setCuotaMensual(double cuotaMensual) {
        this.cuotaMensual = cuotaMensual;
    }
}

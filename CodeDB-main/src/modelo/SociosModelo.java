package modelo;

public class SociosModelo {
    //Atributos
    private static int ultimoNumeroSocio = 1;
    int n_socio;
    String nombre;

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
}

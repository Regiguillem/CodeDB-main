package modelo;

public class FederacionesModelo {
    //Atributos
    String codigo;
    String nombre;

    //Constructor

    public FederacionesModelo(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    //Getters y Setters

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

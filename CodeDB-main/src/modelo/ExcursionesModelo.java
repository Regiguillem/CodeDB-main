package modelo;

import java.time.LocalDate;

public class ExcursionesModelo {

    // atributos
    private String codigo;
    private String descripcion;
    private LocalDate fecha;
    private int n_dias;
    private double precio;

    //constructor
    public ExcursionesModelo(String codigo, String descripcion, LocalDate fecha, int n_dias, double precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.n_dias = n_dias;
        this.precio = precio;
    }

    //getters y setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getN_dias() {
        return n_dias;
    }

    public void setN_dias(int n_dias) {
        this.n_dias = n_dias;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}

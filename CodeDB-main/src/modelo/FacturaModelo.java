package modelo;

import modelo.SociosModelo;

import java.time.LocalDate;

public class FacturaModelo {
    //Atributos
    private static int facturaID = 1;
    int id;
    LocalDate fecha;
    double importe;
    SociosModelo socio;
    //Constructor
    public FacturaModelo(LocalDate fecha, double importe, SociosModelo socio) {
        this.id = facturaID++;
        this.fecha = fecha;
        this.importe = importe;
        this.socio = socio;
    }
    //Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public SociosModelo getSocio() {
        return socio;
    }

    public void setSocio(SociosModelo socio) {
        this.socio = socio;
    }
}

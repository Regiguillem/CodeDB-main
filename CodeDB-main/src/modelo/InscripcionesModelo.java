package modelo;

import modelo.SociosModelo;

import java.time.LocalDate;

public class InscripcionesModelo {
    //Atributos
    int n_inscripcion;
    SociosModelo socio;
    ExcursionesModelo excursion;

    //Constructor

    public InscripcionesModelo(int n_inscripcion, SociosModelo socio, ExcursionesModelo excursion) {
        this.n_inscripcion = n_inscripcion;
        this.socio = socio;
        this.excursion = excursion;
    }

    //Getters y Setters

    public int getN_inscripcion() {
        return n_inscripcion;
    }

    public void setN_inscripcion(int n_inscripcion) {
        this.n_inscripcion = n_inscripcion;
    }

    public SociosModelo getSocio() {
        return socio;
    }

    public void setSocio(SociosModelo socio) {
        this.socio = socio;
    }

    public ExcursionesModelo getExcursion() {
        return excursion;
    }

    public void setExcursion(ExcursionesModelo excursion) {
        this.excursion = excursion;
    }
}

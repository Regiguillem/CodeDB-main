package modelo;

import java.util.ArrayList;
import java.util.List;
import modelo.*;


//PASAMOS DE ARRAYLIST A JDBC. AHORA CADA CAMPO DE LA CLASE DATOS
//ES UNA INSTANCIA DE LAS CLASES DAO

public class Datos {
    private ExcursionesModeloDAO excursionesDAO;
    private SociosModeloDAO sociosDAO;
    private InscripcionesModeloDAO inscripcionesDAO;

    public Datos() {
        this.excursionesDAO = new ExcursionesModeloDAO();
        this.sociosDAO = new SociosModeloDAO();
        this.inscripcionesDAO = new InscripcionesModeloDAO();
    }

    private static Datos instancia;

        // Método estático para obtener la instancia única de la clase
    public static Datos getInstance() {
        // Si la instancia aún no ha sido creada, crear una nueva
        if (instancia == null) {
            instancia = new Datos();
        }
        return instancia;
    }


    public ExcursionesModeloDAO getExcursionesDAO() {
        return excursionesDAO;
    }

    public SociosModeloDAO getSociosDAO() {
        return sociosDAO;
    }

    public InscripcionesModeloDAO getInscripcionesDAO() {
        return inscripcionesDAO;
    }
}

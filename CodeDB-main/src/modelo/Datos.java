package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Datos {

    //Campo para almacenar la única instancia de Datos
    private static Datos instancia = null;

    //Creamos Arrays donde guardaremos todos los datosa
    private ArrayList<ExcursionesModelo> excursiones;
    public ArrayList<SociosModelo> listaSocios;
    private ArrayList<SocioEstandarModelo> sociosEst;
    private ArrayList<SociosFederadosModelo> sociosFed;
    private ArrayList<SocioInfantilModelo> sociosInf;
    public ArrayList<FederacionesModelo> federaciones;
    public ArrayList<SeguroModelo> seguros;
    private ArrayList<InscripcionesModelo> inscripciones;

    //Constructor
    private Datos(){
        //Inicializamos los arrays de datos y otros elementos
        excursiones = new ArrayList<>();
        listaSocios = new ArrayList<>();
        sociosEst = new ArrayList<>();
        sociosFed = new ArrayList<>();
        sociosInf = new ArrayList<>();
        federaciones = new ArrayList<>();
        seguros = new ArrayList<>();
        inscripciones = new ArrayList<>();

        //Creamos las instancias para los dos tipos de seguro
        SeguroCompletoModelo seguroCompleto = new SeguroCompletoModelo();
        SeguroBasicoModelo seguroBasico = new SeguroBasicoModelo();

        //Creamos las instancias de las federaciones para registrar los socios Federadors
        FederacionesModelo fedFEEC = new FederacionesModelo("A001", "FEEC");
        FederacionesModelo fedFEDME = new FederacionesModelo("B001", "FEDME");

        //Creamos instancias para tener socios ya registrados en el programa
        SocioEstandarModelo socioEst = new SocioEstandarModelo("Guillem", "53316605Y", seguroCompleto);
        SociosFederadosModelo socioFed = new SociosFederadosModelo("Sergi", "45678925J", fedFEDME);
        SocioInfantilModelo socioInf = new SocioInfantilModelo("Andrea", socioEst);

        // Agregar elementos a los arrays de datos según sea necesario
        excursiones.add(new ExcursionesModelo("A190", "Montaña", LocalDate.of(2024, 3, 28), 3, 120));
        excursiones.add(new ExcursionesModelo("B200", "Playa", LocalDate.of(2024, 4, 30), 1, 50));
        federaciones.add(fedFEEC);
        federaciones.add(fedFEDME);
        seguros.add(seguroCompleto);
        seguros.add(seguroBasico);
        sociosEst.add(socioEst);
        sociosFed.add(socioFed);
        sociosInf.add(socioInf);
        listaSocios.add(socioEst);
        listaSocios.add(socioFed);
        listaSocios.add(socioInf);
    }

    //Getters
    public ArrayList<ExcursionesModelo> getExcursiones() {
        return excursiones;
    }

    public ArrayList<SociosModelo> getListaSocios() {
        return listaSocios;
    }

    public ArrayList<SocioEstandarModelo> getSociosEst() {
        return sociosEst;
    }

    public ArrayList<SociosFederadosModelo> getSociosFed() {
        return sociosFed;
    }

    public ArrayList<SocioInfantilModelo> getSociosInf() {
        return sociosInf;
    }

    public ArrayList<FederacionesModelo> getFederaciones() {
        return federaciones;
    }

    public ArrayList<SeguroModelo> getSeguros() {
        return seguros;
    }

    public ArrayList<InscripcionesModelo> getInscripciones() {
        return inscripciones;
    }

    //Método estático para acceder a la instancia única que nos proporcionará los Datos iniciales
    public static Datos getInstance() {
        // Si la instancia aún no ha sido creada, la creamos
        if (instancia == null) {
            instancia = new Datos();
        }
        // Devolvemos la instancia existente o recién creada
        return instancia;
    }

    public SeguroCompletoModelo getSeguroCompleto() {
        for (SeguroModelo seguro : seguros) {
            if (seguro instanceof SeguroCompletoModelo) {
                return (SeguroCompletoModelo) seguro;
            }
        }
        return null;
    }

    public SeguroBasicoModelo getSeguroBasico() {
        for (SeguroModelo seguro : seguros) {
            if (seguro instanceof SeguroBasicoModelo) {
                return (SeguroBasicoModelo) seguro;
            }
        }
        return null;
    }
}

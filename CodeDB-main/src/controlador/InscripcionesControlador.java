package controlador;

import modelo.*;
import vista.InscripcionesVista;
import modelo.Datos;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
public class InscripcionesControlador {


        // Atributo para acceder a la capa de acceso a datos para las inscripciones
        private final InscripcionesModeloDAO inscripcionesModeloDAO;

        // Constructor que recibe un objeto InscripcionesModeloDAO
        public InscripcionesControlador(InscripcionesModeloDAO inscripcionesModeloDAO) {
            this.inscripcionesModeloDAO = inscripcionesModeloDAO;
        }

        // Método para obtener todas las inscripciones
        public List<InscripcionesModelo> mostrarTodasInscripciones() {
            return inscripcionesModeloDAO.obtenerTodos();
        }

        // Método para agregar una nueva inscripción a la base de datos
        public InscripcionesModelo agregarNuevaInscripcion(SociosModelo socio, ExcursionesModelo excursion) {
            // Crear una nueva inscripción con los parámetros proporcionados
            InscripcionesModelo nuevaInscripcion = new InscripcionesModelo(socio, excursion, LocalDate.now());

            // Llamar al método insertar en InscripcionesModeloDAO para almacenar la nueva inscripción en la base de datos
            inscripcionesModeloDAO.insertar(nuevaInscripcion);

            return nuevaInscripcion;
        }




    /////***CREO QUE LA PARTE DE ABAJO SE TENDRÍA QUE ELIMINAR PORQUE VAMOS A
    //PASAR DE ARRAYLIST A UTILIZAR JDBC

    // Atributos para relacionar la vista con el ArrayList de inscripciones
 //   private InscripcionesVista vistaInsc;

 //   private Datos datos = Datos.getInstance();
    // Constructor
 //   public InscripcionesControlador() {
 //       this.vistaInsc = new InscripcionesVista();


//método para obtener la instancia de InscripcionesVista
  //  public InscripcionesVista obtenerVistaInscripciones() {
  //      return this.vistaInsc;




//Llamamos al método iniciar de la clase Main
    public boolean iniciar() {
        InscripcionesVista vistaInsc = new InscripcionesVista(); // Crear una instancia de InscripcionesVista
        return vistaInsc.iniciar(); // Llamar al método iniciar() de InscripcionesVista
    }


}

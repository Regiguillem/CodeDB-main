package controlador;

import modelo.InscripcionesModelo;
import modelo.ExcursionesModelo;
import modelo.SociosModelo;
import vista.InscripcionesVista;
import modelo.Datos;

import java.time.LocalDate;
import java.util.ArrayList;

public class InscripcionesControlador {

    // Atributos para relacionar la vista con el ArrayList de inscripciones
    private InscripcionesVista vistaInsc;

    private Datos datos = Datos.getInstance();
    // Constructor
    public InscripcionesControlador() {
        this.vistaInsc = new InscripcionesVista();
    }

    // Método para iniciar el controlador y manejar el menú
    public boolean iniciar() {
        int opcion;
        do {
            opcion = vistaInsc.mostrarMenu();
            switch (opcion) {
                case 1:
                    agregarInscripcion();
                    break;
                case 2:
                    mostrarInscripcion();
                    break;
                case 3:
                    //eliminarInscripcion();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    return true;
                default:
                    System.out.println("Opción no válida. Introduzca una opción válida.");
            }
        } while (opcion != 4);
        return false;
    }

    // Método para agregar una inscripción solicitando datos al usuario
    private void agregarInscripcion() {
        System.out.println("Añadiendo inscripción...");
        int nInscripcion = vistaInsc.solicitarNumeroInscripcion();
        SociosModelo socio = vistaInsc.solicitarSocio();
        ExcursionesModelo excursion = vistaInsc.solicitarExcursion();
        if (socio == null) {
            //Si el socio no existe solicitamos sus datos para añadirlo a la base de datos
            int opcion = vistaInsc.agregarSocioInsc();
            SociosControlador sociosControlador = new SociosControlador(); // Crear una instancia del controlador de socios
            switch (opcion) {
                case 1:
                    sociosControlador.agregarSocioEstandar();
                    break;
                case 2:
                    sociosControlador.agregarSocioFederado();
                    break;
                case 3:
                    sociosControlador.agregarSocioInfantil();
                    break;
                case 0:
                    System.out.println("Saliendo del menú de agregar socio...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
            socio = datos.getListaSocios().get(datos.getListaSocios().size() - 1); // Obtener el último socio agregado
        }
        InscripcionesModelo inscripcion = new InscripcionesModelo(nInscripcion, socio, excursion);
        datos.getInscripciones().add(inscripcion); // Agregar la inscripción a la lista de inscripciones en Datos
        System.out.println("Inscripción añadida correctamente.");
    }

    public void mostrarInscripcion() {
        // Obtener el socio seleccionado
        SociosModelo socioSeleccionado = vistaInsc.solicitarSocio();

        // Verificar si se seleccionó un socio válido
        if (socioSeleccionado != null) {
            // Obtener la lista de inscripciones del socio seleccionado
            ArrayList<InscripcionesModelo> inscripcionesSocio = new ArrayList<>();
            for (InscripcionesModelo inscripcion : datos.getInscripciones()) {
                if (inscripcion.getSocio().equals(socioSeleccionado)) {
                    inscripcionesSocio.add(inscripcion);
                }
            }

            // Verificar si el socio tiene inscripciones
            if (!inscripcionesSocio.isEmpty()) {
                // Mostrar las inscripciones del socio seleccionado
                System.out.println("Inscripciones de " + socioSeleccionado.getNombre() + ":");
                for (InscripcionesModelo inscripcion : inscripcionesSocio) {
                    System.out.println("Número de Inscripción: " + inscripcion.getN_inscripcion());
                    System.out.println("Excursión: " + inscripcion.getExcursion().getDescripcion());
                    System.out.println("--------------------");
                }
            } else {
                System.out.println("El socio seleccionado no tiene inscripciones.");
            }
        }
    }

    // Método para eliminar una inscripción

}

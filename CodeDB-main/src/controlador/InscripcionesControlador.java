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
                    eliminarInscripcion();
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
        SociosModelo socio = vistaInsc.solicitarSocio();
        ExcursionesModelo excursion = vistaInsc.solicitarExcursion();
        LocalDate fechaInscripcion = LocalDate.now();
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
        InscripcionesModelo inscripcion = new InscripcionesModelo(socio, excursion, fechaInscripcion);
        datos.getInscripciones().add(inscripcion); // Agregar la inscripción a la lista de inscripciones en Datos
        System.out.println("Inscripción añadida correctamente.");
    }

    public void mostrarInscripcion() {
        // Obtener el socio seleccionado
        SociosModelo socioSeleccionado = vistaInsc.listarSocios();

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
                    System.out.println("Fecha de Inscripción: " + inscripcion.getFechaInscripcion());
                    System.out.println("Excursión: " + inscripcion.getExcursion().getDescripcion());
                    System.out.println("Fecha de la excursión: " + inscripcion.getExcursion().getFecha());
                    System.out.println("--------------------");
                }
            } else {
                System.out.println("El socio seleccionado no tiene inscripciones.");
            }
        }
    }

    // Método para eliminar una inscripción
    public void eliminarInscripcion() {
        // Obtener todas las inscripciones
        ArrayList<InscripcionesModelo> inscripciones = datos.getInscripciones();

        // Mostrar las inscripciones para que el usuario elija cuál eliminar
        System.out.println("Inscripciones disponibles para eliminar:");
        for (InscripcionesModelo inscripcion : inscripciones) {
            // Verificar si la fecha de inscripción es anterior a la fecha de la excursión
            if (inscripcion.getFechaInscripcion().isBefore(inscripcion.getExcursion().getFecha())) {
                System.out.println("Número de Inscripción: " + inscripcion.getN_inscripcion());
                System.out.println("Fecha de Inscripción: " + inscripcion.getFechaInscripcion());
                System.out.println("Excursión: " + inscripcion.getExcursion().getDescripcion());
                System.out.println("Fecha de la excursión: " + inscripcion.getExcursion().getFecha());
                System.out.println("--------------------");
            }
        }

        // Solicitar al usuario que seleccione una inscripción para eliminar
        System.out.println("Seleccione el número de inscripción que desea eliminar (0 para cancelar): ");
        int numeroInscripcion = vistaInsc.solicitarNumeroInscripcion();

        // Verificar si el usuario canceló la operación
        if (numeroInscripcion == 0) {
            System.out.println("Operación cancelada.");
            return;
        }

        // Buscar la inscripción correspondiente al número seleccionado
        InscripcionesModelo inscripcionSeleccionada = null;
        for (InscripcionesModelo inscripcion : inscripciones) {
            if (inscripcion.getN_inscripcion() == numeroInscripcion) {
                inscripcionSeleccionada = inscripcion;
                break;
            }
        }

        // Verificar si se encontró la inscripción seleccionada
        if (inscripcionSeleccionada != null) {
            // Verificar si la fecha de la excursión ya ha pasado
            if (inscripcionSeleccionada.getExcursion().getFecha().isBefore(LocalDate.now())) {
                System.out.println("La fecha de la excursión ya ha pasado. No se puede eliminar la inscripción.");
            } else {
                // Eliminar la inscripción
                inscripciones.remove(inscripcionSeleccionada);
                System.out.println("Inscripción eliminada correctamente.");
            }
        } else {
            System.out.println("No se encontró ninguna inscripción con el número especificado.");
        }
    }
}

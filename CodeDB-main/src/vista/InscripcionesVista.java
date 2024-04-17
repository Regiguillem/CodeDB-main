package vista;

import controlador.ExcursionesControlador;
import controlador.SociosControlador;
import controlador.InscripcionesControlador;
import modelo.ExcursionesModelo;
import modelo.InscripcionesModelo;
import modelo.SociosModelo;

import modelo.Datos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class InscripcionesVista {

    private Scanner scanner;
    private ExcursionesControlador excursionesControlador;
    private SociosControlador sociosControlador;
    private SociosVista sociosVista;
    private Datos datos = Datos.getInstance();

    public InscripcionesVista() {
        this.scanner = new Scanner(System.in);
        this.excursionesControlador = new ExcursionesControlador();
        this.sociosControlador = new SociosControlador();
        this.sociosVista = new SociosVista();
    }



    // Método para mostrar el menú de gestión de inscripciones
    public int mostrarMenu() {
        System.out.println("--GESTIÓN INSCRIPCIONES--");
        System.out.println("1. Añadir Inscripción");
        System.out.println("2. Mostrar Inscripciones con filtro por socio");
        System.out.println("3. Eliminar Inscripción");
        System.out.println("0. Volver al menú principal");
        System.out.println("Seleccione una opción: ");
        return scanner.nextInt();
    }

    // Método para iniciar el controlador y manejar el menú
    public boolean iniciar() {
        int opcion;
        InscripcionesControlador controlador = new InscripcionesControlador();
        do {
            opcion = controlador.obtenerVistaInscripciones().mostrarMenu();
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
        InscripcionesControlador controlador = new InscripcionesControlador();
        SociosModelo socio = controlador.obtenerVistaInscripciones().solicitarSocio();
        ExcursionesModelo excursion = controlador.obtenerVistaInscripciones().solicitarExcursion();
        LocalDate fechaInscripcion = LocalDate.now();
        // Verificar si la fecha de hoy es posterior a la fecha de la excursión
        if (fechaInscripcion.isAfter(excursion.getFecha())) {
            System.out.println("No se puede realizar la inscripción porque la fecha de hoy es posterior a la fecha de la excursión.");
            return;
        }
        if (socio == null) {
            //Si el socio no existe solicitamos sus datos para añadirlo a la base de datos
            int opcion = controlador.obtenerVistaInscripciones().agregarSocioInsc();
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
        InscripcionesControlador controlador = new InscripcionesControlador();
        SociosModelo socioSeleccionado = controlador.obtenerVistaInscripciones().listarSocios();

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
        InscripcionesControlador controlador = new InscripcionesControlador();
        int numeroInscripcion = controlador.obtenerVistaInscripciones().solicitarNumeroInscripcion();

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



    // Método para solicitar datos de una inscripción al usuario
    public int solicitarNumeroInscripcion() {
        System.out.println("Introduzca el número de inscripción:");
        int n_inscripcion = scanner.nextInt();
        return n_inscripcion;
    }

    public ExcursionesModelo solicitarExcursion() {
        System.out.println("Seleccione la excursión: ");
        excursionesControlador.mostrarExcursiones(); //Mostramos la lista de excursiones
        System.out.println("Ingrese el código de la excursión: ");
        String codigoEx = scanner.nextLine().toUpperCase();
        return excursionesControlador.obtenerExcursionPorCodigo(codigoEx);
    }

    public SociosModelo solicitarSocio(){
        System.out.println("Seleccione el socio que desea inscribirse: ");
        sociosVista.mostrarSocios();
        System.out.println("Íngrese el código del socio que quiere inscribirse: ");
        int n_socio = scanner.nextInt();
        scanner.nextLine();
        return sociosControlador.obtenerSocioPorCodigo(n_socio);
    }

    public int agregarSocioInsc(){
        System.out.println("Añadiendo nuevo socio: ");
        System.out.println("Qué tipo de socio desea añadir?");
        System.out.println("1. Socio Estándar");
        System.out.println("2. Socio Federado");
        System.out.println("3. Socio Infantil");
        System.out.println("0. Salir");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        return opcion;
    }

    public SociosModelo listarSocios(){
        System.out.println("Seleccione el socio para mostrar sus inscripciones: ");
        sociosVista.mostrarSocios();
        System.out.println("Íngrese el código del socio: ");
        int n_socio = scanner.nextInt();
        scanner.nextLine();
        return sociosControlador.obtenerSocioPorCodigo(n_socio);
    }
    // Método para mostrar un mensaje
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}

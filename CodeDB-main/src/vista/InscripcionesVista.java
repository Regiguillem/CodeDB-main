package vista;

import controlador.ExcursionesControlador;
import controlador.SociosControlador;
import modelo.ExcursionesModelo;
import modelo.InscripcionesModelo;
import modelo.SociosModelo;
import vista.SociosVista;
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
        System.out.println("2. Mostrar Inscripciones con filtro por fecha y/o socios");
        System.out.println("3. Eliminar Inscripción");
        System.out.println("0. Volver al menú principal");
        System.out.println("Seleccione una opción: ");
        return scanner.nextInt();
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
        String codigoEx = scanner.nextLine();
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



    // Método para solicitar fechas para filtrar inscripciones
    public LocalDate[] fechasFiltroInscripciones() {
        LocalDate[] fechas = new LocalDate[2];
        System.out.println("Mostrar Inscripciones con filtro por fecha");
        System.out.println("Introduzca la fecha de inicio (YYYY-MM-DD):");
        fechas[0] = LocalDate.parse(scanner.next());
        scanner.nextLine();
        System.out.println("Introduzca la fecha de fin (YYYY-MM-DD):");
        fechas[1] = LocalDate.parse(scanner.next());
        scanner.nextLine();
        return fechas;
    }

    // Método para mostrar una inscripción
    public void mostrarInscripcion(InscripcionesModelo inscripcion) {
        System.out.println("---------------");
        System.out.println("Número de Socio: " + inscripcion.getSocio().getN_socio());
        System.out.println("Nombre: " + inscripcion.getSocio().getNombre());
        System.out.println("Fecha de Excursión: " + inscripcion.getExcursion().getFecha());
        System.out.println("Descripción: " + inscripcion.getExcursion().getDescripcion());
        System.out.println("Importe: " + inscripcion.getExcursion().getPrecio());
        System.out.println("---------------");
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

    // Método para mostrar un mensaje
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}

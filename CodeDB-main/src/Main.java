import controlador.*;
import modelo.Datos;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Datos datos = Datos.getInstance();
        Scanner scanner = new Scanner(System.in);

        int opcion;

        do {
            // Menú Principal
            System.out.println("Menú Principal");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Controlador Excursiones");
            System.out.println("2. Controlador Socios");
            System.out.println("3. Controlador Inscripciones");
            System.out.println("0. Salir");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    ExcursionesControlador controladorEx = new ExcursionesControlador();
                    if (!controladorEx.iniciar()) {
                        System.out.println("Volviendo al menú principal...");
                    }
                    break;
                case 2:
                    SociosControlador controladorSoc = new SociosControlador();
                    if (!controladorSoc.iniciar()) {
                        System.out.println("Volviendo al menú principal...");
                    }
                    break;
                case 3:
                    InscripcionesControlador controladorInsc = new InscripcionesControlador();
                    if (!controladorInsc.iniciar()) {
                        System.out.println("Volviendo al menú principal...");
                    }
                    break;
                case 0:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}
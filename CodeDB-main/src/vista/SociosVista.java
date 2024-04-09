package vista;
import controlador.SociosControlador;
import modelo.*;

import java.util.ArrayList;
import java.util.Scanner;

public class SociosVista {
    private Scanner scanner;
    private Datos datos = Datos.getInstance();

    public SociosVista() {
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenu() {
        System.out.println("Gestión de Socios");
        System.out.println("1. Añadir Socio Estándar");
        System.out.println("2. Modificar tipo de seguro de un socio estándar");
        System.out.println("3. Añadir Socio Federado");
        System.out.println("4. Añadir Socio Infantil");
        System.out.println("5. Eliminar socio");
        System.out.println("6. Mostrar socios filtrados:");
        System.out.println("7. Mostrar factura mensual de socio:");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
        return scanner.nextInt();
    }

    public int solicitarNuevoTipoSeguro() {
        System.out.println("Introduzca el nuevo tipo de seguro: ");
        System.out.println("1. Seguro Completo");
        System.out.println("2. Seguro Básico");
        System.out.print("Elija una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner
        return opcion;
    }

    public void mostrarMensaje (String mensaje){
        System.out.println(mensaje);
    }

    // Métodos para solicitar información al usuario
    public int solicitarNumeroSocio() {
        System.out.print("Introduzca el número de socio: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();
        return codigo;
    }

    public String solicitarNombreSocio() {
        System.out.print("Introduzca el nombre del socio: ");
        return scanner.nextLine();
    }

    public String solicitarNifSocio() {
        System.out.print("Introduzca el NIF del socio: ");
        return scanner.nextLine();
    }

    public SeguroModelo solicitarSeguroSocio() {
        System.out.println("Seleccione el tipo de seguro para el socio:");
        System.out.println("1. Seguro Completo");
        System.out.println("2. Seguro Básico");
        System.out.print("Elija una opción: ");

        int opcionSeguro = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner

        switch (opcionSeguro) {
            case 1:
                return new SeguroCompletoModelo();
            case 2:
                return new SeguroBasicoModelo();
            default:
                System.out.println("Opción no válida. Seleccionando Seguro Básico por defecto.");
                return new SeguroBasicoModelo();
        }
    }

    public int opcionFederacion(){
        System.out.println("Seleccione una opción válida: ");
        int opcionFed = scanner.nextInt();
        return opcionFed;
    }

    public int solicitarNumeroSocioPadreMadre() {
        System.out.print("Introduzca el número de socio del padre o madre: ");
        return scanner.nextInt();
    }

    public void mostrarSocioInfantil(SocioInfantilModelo socioInfantil, String nombrePadreMadre) {
        System.out.println("Socio Infantil añadido correctamente:");
        System.out.println("Número de socio: " + socioInfantil.getN_socio());
        System.out.println("Nombre del socio: " + socioInfantil.getNombre());
        System.out.println("Número de socio del padre o madre: " + socioInfantil.getN_socioPadreMadre().getN_socio() + " (Nombre: " + nombrePadreMadre + ")");
        System.out.println("Descuento en cuota: " + socioInfantil.getDescuento_cuota() + "%");
    }

    public void mostrarSociosPorTipo() {
        System.out.println("Seleccione el tipo de socios que desea ver:");
        System.out.println("1. Socios Estándar");
        System.out.println("2. Socios Federados");
        System.out.println("3. Socios Infantiles");
        System.out.println("4. Mostrar todos los socios");
        System.out.println("0. Volver al menú anterior");

        int opcion = scanner.nextInt();
        scanner.nextLine();
        ArrayList<SocioEstandarModelo> listaEstandar = datos.getSociosEst();
        ArrayList<SociosFederadosModelo> listaFederada = datos.getSociosFed();
        ArrayList<SocioInfantilModelo> listaInfantil = datos.getSociosInf();

        switch (opcion) {
            case 1:
                System.out.println("Mostrando socios estándar:");
                mostrarSociosEst(listaEstandar);
                break;
            case 2:
                System.out.println("Mostrando socios federados:");
                mostrarSociosFed(listaFederada);
                break;
            case 3:
                System.out.println("Mostrando socios infantiles:");
                mostrarSociosInf(listaInfantil);
                break;
            case 4:
                System.out.println("Mostrando todos los socios:");
                mostrarSocios();
                break;
            case 0:
                System.out.println("Volviendo al menú anterior.");
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    public void mostrarSociosEst(ArrayList<SocioEstandarModelo> sociosEst) {
        System.out.println("Lista de Socios Estándar Disponibles:");
        for (SocioEstandarModelo socio : sociosEst) {
            System.out.println("Número de socio: " + socio.getN_socio() + ", Nombre: " + socio.getNombre() + ", Tipo de Seguro: " + socio.getSeguro().getTipo());
        }
    }

    public void mostrarSociosFed(ArrayList<SociosFederadosModelo> listaFederada) {
        if (listaFederada.isEmpty()) {
            System.out.println("No hay socios federados.");
        } else {
            System.out.println("Lista de socios federados:");
            for (SociosFederadosModelo socio : listaFederada) {
                System.out.println("Nombre: " + socio.getNombre() + ", NIF: " + socio.getNif() + ", Código Federación: " + socio.getFederacion().getNombre());
            }
        }
    }

    public void mostrarSociosInf(ArrayList<SocioInfantilModelo> listaInfantil) {
        if (listaInfantil.isEmpty()) {
            System.out.println("No hay socios infantiles.");
        } else {
            System.out.println("Lista de socios infantiles:");
            for (SocioInfantilModelo socio : listaInfantil) {
                System.out.println("Nombre: " + socio.getNombre() + ", Socio Padre/Madre: " + socio.getN_socioPadreMadre().getNombre());
            }
        }
    }

    public void mostrarSocios() {
        for (SociosModelo socio : datos.getListaSocios()) {
            System.out.println("Número de socio: " + socio.getN_socio());
            System.out.println("Nombre: " + socio.getNombre());
            // Comprobar el tipo de socio y mostrar información específica según el tipo
            if (socio instanceof SocioEstandarModelo) {
                SocioEstandarModelo socioEstandar = (SocioEstandarModelo) socio;
                System.out.println("Tipo de socio: Estándar");
                // Mostrar más información específica del socio estándar si es necesario
                System.out.println("NIF: " + socioEstandar.getNif());
                System.out.println("Seguro: " + socioEstandar.getSeguro().getTipo());
            } else if (socio instanceof SociosFederadosModelo) {
                SociosFederadosModelo socioFederado = (SociosFederadosModelo) socio;
                System.out.println("Tipo de socio: Federado");
                // Mostrar más información específica del socio federado si es necesario
                System.out.println("NIF: " + socioFederado.getNif());
                System.out.println("Federación: " + socioFederado.getFederacion().getNombre());
            } else if (socio instanceof SocioInfantilModelo) {
                SocioInfantilModelo socioInfantil = (SocioInfantilModelo) socio;
                System.out.println("Tipo de socio: Infantil");
                // Mostrar más información específica del socio infantil si es necesario
                System.out.println("Número de socio del Padre/Madre: " + socioInfantil.getN_socioPadreMadre().getN_socio());
                System.out.println("Descuento Cuota: " + socioInfantil.getDescuento_cuota());
            }
            System.out.println("------------------------------------------");
        }
    }
    //Faltan argumentos que pedir al usuario?
}

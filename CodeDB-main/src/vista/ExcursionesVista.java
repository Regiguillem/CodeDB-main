package vista;

import modelo.ExcursionesModelo;

import java.time.LocalDate;
import java.util.Scanner;

public class ExcursionesVista {
    //Creamos un scanner para leer los datos de la pantalla
    private Scanner scanner;

    public ExcursionesVista() {
        this.scanner = new Scanner(System.in);
    }

    //Metodo Menú
    public int mostrarMenu() {
        System.out.println("Menú Excursiones:");
        System.out.println("1. Añadir Excursión");
        System.out.println("2. Mostrar Excursiones con filtro entre fechas");
        System.out.println("0. Volver al menú principal");
        System.out.println("Seleccione una opción: ");
        //Devuelve un int que el controlador comprobará para saber qué debe ejecutar
        return scanner.nextInt();
    }

    //La vista pide los datos necesarios para construir el objeto excursión al usuario
    public ExcursionesModelo DatosExcursion() {
        System.out.println("Añadiendo excursión...");
        System.out.println("Introduzca el código:");
        String codigo = scanner.next();
        scanner.nextLine();
        System.out.println("Introduzca la descripción:");
        String descripcion = scanner.nextLine();
        System.out.println("Introduzca la fecha (YYYY-MM-DD):");
        LocalDate fecha = LocalDate.parse(scanner.next());
        scanner.nextLine();
        System.out.println("Introduzca el número de días:");
        int nDias = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduzca el precio de la excursión:");
        double precio = scanner.nextDouble();
        //La misma vista devuelve un nuevo objeto de tipo modelo.ExcursionesModelo y le añade los parámetros para que el constructor lo añada posteriormente al arraylist.
        return new ExcursionesModelo(codigo, descripcion, fecha, nDias, precio);
    }


    public LocalDate[] ExcursionesFechasFiltro(){
        //LocalDate[2] indica que solo habrá dos valores en dicho array: [0] y [1]
        LocalDate[] fechas = new LocalDate[2];

        System.out.println("Mostrar Excursiones con filtro entre fechas");
        System.out.println("Introduzca la fecha de inicio (YYYY-MM-DD):");
        fechas[0] = LocalDate.parse(scanner.next());
        scanner.nextLine();
        System.out.println("Introduzca la fecha de final (YYYY-MM-DD):");
        fechas[1] = LocalDate.parse(scanner.next());
        scanner.nextLine();
        //Devuelve el array al controlador para que éste proceda a hacer el filtro
        return fechas;
    }

    //El controlador le pasará los datos necesarios a la vista para mostrar los datos de las excursiones filtradas
    public void mostrarExcursion(ExcursionesModelo excursion){
        System.out.println("---------------");
        System.out.println("Código: " + excursion.getCodigo());
        System.out.println("Descripcion: " + excursion.getDescripcion());
        System.out.println("Fecha: " + excursion.getFecha());
        System.out.println("Número de días: " + excursion.getN_dias());
        System.out.println("Precio: " + excursion.getPrecio());
        System.out.println("---------------");
    }

    //Lo utilizamos para mostrar mensajes simples desde el controlador.
    //¿Podria ser un Java Generics?
    public void mostrarMensaje (String mensaje){
        System.out.println(mensaje);
    }
}

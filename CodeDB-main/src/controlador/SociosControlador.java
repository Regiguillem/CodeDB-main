package controlador;

import modelo.*;
import vista.SociosVista;

import java.util.ArrayList;

public class SociosControlador {

    //Atributos para relacionar la vista con el arraylist de socios
    private SociosVista vistaSoc;

    private Datos datos = Datos.getInstance();

    public SociosControlador() {
        this.vistaSoc = new SociosVista();
    }

    public boolean iniciar() {
        int opcion;
        do {
            // Extraer la opción del menú desde la vista
            opcion = vistaSoc.mostrarMenu();
            switch (opcion) {
                case 1:
                    agregarSocioEstandar();
                    break;
                case 2:
                    modificarTipoSeguroSocio();
                    break;
                case 3:
                    agregarSocioFederado();
                    break;
                case 4:
                    agregarSocioInfantil();
                    break;
                case 5:
                    //eliminarSocio();
                    break;
                case 6:
                    vistaSoc.mostrarSociosPorTipo();
                case 7:
                    //mostrarFacturaMensualFiltroSocio();
                case 0:
                    System.out.println("Saliendo del menú de Gestión de Socios...");
                    return true;
                default:
                    System.out.println("Opción no válida. Introduzca una opción válida.");
            }
        } while (opcion != 0);
        return false;
    }

    public void agregarSocioEstandar() {
        vistaSoc.mostrarMensaje("Añadiendo Socio Estándar...");

        // Solicitar la información del socio al usuario
        String nombre = vistaSoc.solicitarNombreSocio();
        String nif = vistaSoc.solicitarNifSocio();
        SeguroModelo seguro = vistaSoc.solicitarSeguroSocio();

        // Crear una instancia de SocioEstandarModelo con la información proporcionada por el usuario
        SocioEstandarModelo socioEstandar = new SocioEstandarModelo(nombre, nif, seguro);

        // Agregar el socio a la lista de socios
        datos.getSociosEst().add(socioEstandar);
        datos.getListaSocios().add(socioEstandar);

        vistaSoc.mostrarMensaje("Socio Estándar añadido correctamente.");
    }

    public void modificarTipoSeguroSocio() {
        vistaSoc.mostrarMensaje("Modificando tipo de seguro de un socio estándar...");

        // Mostrar la lista de socios estándar disponibles
        listarSociosEst();

        // Solicitar al usuario que elija el socio cuyo tipo de seguro desea modificar
        int numeroSocio = vistaSoc.solicitarNumeroSocio();
        SocioEstandarModelo socioSeleccionado = buscarSocioEstPorNumero(numeroSocio);

        if (socioSeleccionado != null) {
            // Solicitar al usuario que elija el nuevo tipo de seguro
            int opcionSeguro = vistaSoc.solicitarNuevoTipoSeguro();
            SeguroModelo nuevoSeguro = null;

            // Crear el nuevo tipo de seguro seleccionado por el usuario
            switch (opcionSeguro) {
                case 1:
                    nuevoSeguro = datos.getSeguroCompleto();;
                    break;
                case 2:
                    nuevoSeguro = datos.getSeguroBasico();;
                    break;
                default:
                    vistaSoc.mostrarMensaje("Opción no válida. No se realizarán cambios en el tipo de seguro.");
                    return;
            }

            // Actualizar el tipo de seguro del socio seleccionado
            socioSeleccionado.setSeguro(nuevoSeguro);
            vistaSoc.mostrarMensaje("Tipo de seguro del socio modificado correctamente.");
        } else {
            vistaSoc.mostrarMensaje("El socio con el número especificado no existe.");
        }
    }

    public void listarSociosEst(){
        System.out.println("Lista de Socios Estándar Disponibles:");
        for (SocioEstandarModelo socio : datos.getSociosEst()) {
            System.out.println("Número de socio: " + socio.getN_socio() + ", Nombre: " + socio.getNombre());
        }
    }

    private SocioEstandarModelo buscarSocioEstPorNumero(int numeroSocio) {
        for (SocioEstandarModelo socio : datos.getSociosEst()) {
            if (socio.getN_socio() == numeroSocio) {
                return socio;
            }
        }
        return null; // Retorna null si no se encuentra ningún socio con el número especificado
    }

    public void agregarSocioFederado() {
        vistaSoc.mostrarMensaje("Añadiendo Socio Federado...");

        // Solicitar la información del socio federado al usuario
        String nombre = vistaSoc.solicitarNombreSocio();
        String nif = vistaSoc.solicitarNifSocio();
        FederacionesModelo federacionSocio = solicitarFederacion();

        // Crear una instancia de SociosFederadosModelo con la información proporcionada por el usuario
        SociosFederadosModelo socioFederado = new SociosFederadosModelo(nombre, nif, federacionSocio);

        // Agregar el socio federado a la lista de socios

        datos.getSociosFed().add(socioFederado);
        datos.getListaSocios().add(socioFederado);

        vistaSoc.mostrarMensaje("Socio Federado añadido correctamente.");
    }
    public FederacionesModelo solicitarFederacion() {
        System.out.println("Seleccione la federación del socio:");
        ArrayList<FederacionesModelo> federaciones = datos.getFederaciones();

        // Mostrar las opciones de federaciones disponibles
        for (int i = 0; i < federaciones.size(); i++) {
            FederacionesModelo federacion = federaciones.get(i);
            System.out.println((i + 1) + ". " + federacion.getNombre() + " (Código: " + federacion.getCodigo() + ")");
        }

        int opcionFederacion;
        do {
            opcionFederacion = vistaSoc.opcionFederacion();
            if (opcionFederacion < 1 || opcionFederacion > federaciones.size()) {
                System.out.println("Seleccione una opción válida.");
            }
        } while (opcionFederacion < 1 || opcionFederacion > federaciones.size());

        return federaciones.get(opcionFederacion - 1); // Devolver la federación seleccionada
    }

    public void agregarSocioInfantil() {
        vistaSoc.mostrarMensaje("Añadiendo Socio Infantil...");

        // Solicitar la información del socio infantil al usuario
        String nombre = vistaSoc.solicitarNombreSocio();
        int n_socioPadreMadre = vistaSoc.solicitarNumeroSocioPadreMadre();

        // Buscar el socio padre o madre en la lista de socios estándar
        SocioEstandarModelo socioPadreMadre = buscarSocioEstPorNumero(n_socioPadreMadre);

        // Verificar si se encontró el socio padre o madre
        if (socioPadreMadre != null) {
            // Crear una instancia de SocioInfantilModelo con la información proporcionada por el usuario
            SocioInfantilModelo socioInfantil = new SocioInfantilModelo(nombre, socioPadreMadre);

            // Agregar el socio infantil a la lista de socios infantiles
            datos.getSociosInf().add(socioInfantil);
            datos.getListaSocios().add(socioInfantil);

            vistaSoc.mostrarMensaje("Socio Infantil añadido correctamente.");
            // Mostrar la información del socio infantil agregado
            vistaSoc.mostrarSocioInfantil(socioInfantil, socioPadreMadre.getNombre());
        } else {
            vistaSoc.mostrarMensaje("El socio padre o madre con el número especificado no existe.");
        }
    }

    public SociosModelo obtenerSocioPorCodigo(int codigo) {
        for (SociosModelo socio : datos.getListaSocios()) {
            if (codigo == socio.getN_socio()) {
                return socio;
            }
        }
        return null; // Devuelve null si no se encuentra ningún socio con el código proporcionado
    }
    // Otros métodos para manejar las demás opciones del menú de socios
}
package controlador;

import modelo.*;
import vista.SociosVista;

import java.time.LocalDate;
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
                    eliminarSocio();
                    break;
                case 6:
                    vistaSoc.mostrarSociosPorTipo();
                    break;
                case 7:
                    mostrarFacturaMensualFiltroSocio();
                    break;
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
            System.out.println("Número de socio: " + socio.getN_socio() + ", Nombre: " + socio.getNombre()+ ", Tipo de Seguro: " + socio.getSeguro().getTipo());
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

    public void eliminarSocio() {
        // Obtener todos los socios
        ArrayList<SociosModelo> socios = datos.getListaSocios();

        // Mostrar los socios para que el usuario elija cuál eliminar
        System.out.println("Socios disponibles para eliminar:");
        for (SociosModelo socio : socios) {
            System.out.println("Número de Socio: " + socio.getN_socio() + ", Nombre: " + socio.getNombre());
        }

        // Solicitar al usuario que seleccione un socio para eliminar
        System.out.println("Seleccione el número de socio que desea eliminar (0 para cancelar): ");
        int numeroSocio = vistaSoc.solicitarNumeroSocio();

        // Verificar si el usuario canceló la operación
        if (numeroSocio == 0) {
            System.out.println("Operación cancelada.");
            return;
        }

        // Buscar el socio correspondiente al número seleccionado
        SociosModelo socioSeleccionado = null;
        for (SociosModelo socio : socios) {
            if (socio.getN_socio() == numeroSocio) {
                socioSeleccionado = socio;
                break;
            }
        }

        // Verificar si se encontró el socio seleccionado
        if (socioSeleccionado != null) {
            // Verificar si el socio está inscrito en alguna excursión
            boolean inscrito = false;
            for (InscripcionesModelo inscripcion : datos.getInscripciones()) {
                if (inscripcion.getSocio().equals(socioSeleccionado)) {
                    inscrito = true;
                    break;
                }
            }
            // Si el socio no está inscrito en ninguna excursión, se elimina
            if (!inscrito) {
                socios.remove(socioSeleccionado);
                System.out.println("Socio eliminado correctamente.");
            } else {
                System.out.println("No se puede eliminar el socio porque está inscrito en al menos una excursión.");
            }
        } else {
            System.out.println("No se encontró ningún socio con el número especificado.");
        }
    }

    public void mostrarFacturaMensualFiltroSocio() {
        // Obtener la lista de socios desde el modelo (suponiendo que tienes un método en Datos para obtenerlos)
        ArrayList<SociosModelo> socios = datos.getListaSocios();

        // Solicitar al usuario que seleccione un socio
        SociosModelo socioSeleccionado = vistaSoc.seleccionarSocio(socios);

        // Verificar si se seleccionó un socio válido
        if (socioSeleccionado != null) {
            // Llamar al método mostrarFacturaMensualFiltroSocio con el socio seleccionado
            mostrarFacturaMensualFiltroSocio(socioSeleccionado);
        } else {
            System.out.println("No se seleccionó ningún socio.");
        }
    }

    private void mostrarFacturaMensualFiltroSocio(SociosModelo socio) {
        // Calcular el total a pagar por la cuota mensual
        double totalCuotaMensual = calcularCuotaMensual(socio);

        // Calcular el total a pagar por las excursiones realizadas
        double totalExcursiones = calcularTotalExcursiones(socio);

        // Calcular el total a pagar
        double totalPagar = totalCuotaMensual + totalExcursiones;

        // Mostrar la factura
        System.out.println("Factura mensual para el socio " + socio.getNombre() + ":");
        System.out.println("Fecha: " + LocalDate.now());
        System.out.println("Cuota Mensual: " + totalCuotaMensual);
        System.out.println("Total Excursiones: " + totalExcursiones);
        // Ajustar el total a pagar si el socio es de tipo SocioEstandarModelo
        if (socio instanceof SocioEstandarModelo) {
            SocioEstandarModelo socioEstandar = (SocioEstandarModelo) socio;
            totalPagar += socioEstandar.getSeguro().getPrecio();
            System.out.println("Precio del seguro: " + socioEstandar.getSeguro().getPrecio());
        }

        // Mostrar descuentos aplicados para socios Federados e Infantiles
        if (socio instanceof SociosFederadosModelo) {
            System.out.println("Descuento en la cuota aplicado: " + ((SociosFederadosModelo) socio).getDescuento_cuota() * 100 + "%");
            System.out.println("Descuento aplicado en las excursiones: " + ((SociosFederadosModelo) socio).getDescuento_exc() * 100 + "%");
        } else if (socio instanceof SocioInfantilModelo) {
            System.out.println("Descuento en la cuota aplicado: " + ((SocioInfantilModelo) socio).getDescuento_cuota() * 100 + "%");
        }
        System.out.println("-----------------------------");
        System.out.println("Total a Pagar: " + totalPagar);
        System.out.println("-----------------------------");
    }

    private double calcularCuotaMensual(SociosModelo socio) {
        double cuotaBaseMensual = socio.getCuotaMensual();
        double descuentoCuota = 0;

        if (socio instanceof SociosFederadosModelo) {
            // Socio federado tiene descuento del 5%
            descuentoCuota = cuotaBaseMensual * ((SociosFederadosModelo) socio).getDescuento_cuota();
        } else if (socio instanceof SocioInfantilModelo) {
            // Socio infantil tiene descuento del 50%
            descuentoCuota = cuotaBaseMensual * ((SocioInfantilModelo) socio).getDescuento_cuota();
        }

        return cuotaBaseMensual - descuentoCuota;
    }

    private double calcularTotalExcursiones(SociosModelo socio) {
        double totalExcursiones = 0;
        ArrayList<InscripcionesModelo> inscripciones = datos.getInscripciones();
        LocalDate fechaActual = LocalDate.now();
        int mesActual = fechaActual.getMonthValue();

        for (InscripcionesModelo inscripcion : inscripciones) {
            if (inscripcion.getSocio().equals(socio)) { // Verificar si la inscripción pertenece al socio
                ExcursionesModelo excursion = inscripcion.getExcursion();
                LocalDate fechaExcursion = excursion.getFecha();
                int mesExcursion = fechaExcursion.getMonthValue();

                // Verificar si la excursión es del mes actual
                if (mesExcursion == mesActual) {
                    double precioExcursion = excursion.getPrecio();

                    if (socio instanceof SociosFederadosModelo) {
                        // Socio federado tiene descuento del 10%
                        precioExcursion *= ((SociosFederadosModelo) socio).getDescuento_exc();
                    }

                    totalExcursiones += precioExcursion;
                }
            }
        }

        return totalExcursiones;
    }
    // Otros métodos para manejar las demás opciones del menú de socios
}
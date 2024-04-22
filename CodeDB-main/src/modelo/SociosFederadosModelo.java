package modelo;
public class SociosFederadosModelo extends SociosModelo {
    //Atributos
    private String nif;
    private FederacionesModelo federacion;
    private double descuento_cuota;
    private double descuento_exc;

    //Constructor

    public SociosFederadosModelo(String nombre, String nif, FederacionesModelo federacion) {
        super(nombre);
        this.nif = nif;
        this.federacion = federacion;
        this.descuento_cuota = 0.05;
        this.descuento_exc = 0.1;
    }


    //Getters y Setters

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public FederacionesModelo getFederacion() {
        return federacion;
    }

    public void setFederacion(FederacionesModelo federacion) {
        this.federacion = federacion;
    }

    public double getDescuento_cuota() {
        return descuento_cuota;
    }

    public void setDescuento_cuota(double descuento_cuota) {
        this.descuento_cuota = descuento_cuota;
    }

    public double getDescuento_exc() {
        return descuento_exc;
    }

    public void setDescuento_exc(double descuento_exc) {
        this.descuento_exc = descuento_exc;
    }
    public String tipoSocio() {
        return "Federado";
    }
}

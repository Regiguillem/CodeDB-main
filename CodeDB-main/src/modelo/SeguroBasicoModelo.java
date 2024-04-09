package modelo;

public class SeguroBasicoModelo extends SeguroModelo {
    //Atributos
    String tipo;
    double precio;

    //Constructor
    public SeguroBasicoModelo() {
        tipo = "Básico";
        precio = 50;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecio() {
        return precio;
    }
    //El precio del seguro no se especifica, por lo que por el momento será 50 euros.
}

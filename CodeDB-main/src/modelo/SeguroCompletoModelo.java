package modelo;

public class SeguroCompletoModelo extends  SeguroModelo{
    //Atributos
    String tipo;
    double precio;

    //Constructor
    public SeguroCompletoModelo() {
        tipo = "Completo";
        precio = 100;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecio() {
        return precio;
    }

    //El precio del seguro no se especifica, por lo que por el momento será 100 euros.
}

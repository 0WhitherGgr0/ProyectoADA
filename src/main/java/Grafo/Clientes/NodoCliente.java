package Grafo.Clientes;

/**
 * Clase que representa un nodo cliente en un grafo.
 */
public class NodoCliente {
    private String nombre;   // Nombre del nodo cliente

    /**
     * Constructor de la clase NodoCliente.
     * @param nombre Nombre del nodo cliente.
     */
    public NodoCliente(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método getter para obtener el nombre del nodo cliente.
     * @return Nombre del nodo cliente.
     */
    public String getNombre() {
        return nombre;
    }
}

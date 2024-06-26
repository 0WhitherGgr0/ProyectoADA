package Grafo.Clientes;

/**
 * Clase que representa un nodo base en un grafo.
 */
public class NodoBase {
    private String nombre;   // Nombre del nodo base

    /**
     * Constructor de la clase NodoBase.
     * @param nombre Nombre del nodo base.
     */
    public NodoBase(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método getter para obtener el nombre del nodo base.
     * @return Nombre del nodo base.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método setter para establecer el nombre del nodo base.
     * @param nombre Nuevo nombre para el nodo base.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

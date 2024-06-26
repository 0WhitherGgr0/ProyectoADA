package Grafo.Clientes;

/**
 * Clase que representa un nodo en un grafo.
 */
public class Nodo {
    private String nombre;   // Nombre del nodo
    private boolean esBase;  // Indicador de si es nodo base o no

    /**
     * Constructor de la clase Nodo.
     * @param nombre Nombre del nodo.
     * @param esBase Indica si el nodo es el nodo base.
     */
    public Nodo(String nombre, boolean esBase) {
        this.nombre = nombre;
        this.esBase = esBase;
    }

    /**
     * Método getter para obtener el nombre del nodo.
     * @return Nombre del nodo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método getter para verificar si el nodo es el nodo base.
     * @return true si el nodo es el nodo base, false en caso contrario.
     */
    public boolean esBase() {
        return esBase;
    }

    /**
     * Método setter para establecer si el nodo es el nodo base.
     * @param esBase true si se desea establecer como nodo base, false en caso contrario.
     */
    public void setEsBase(boolean esBase) {
        this.esBase = esBase;
    }
}

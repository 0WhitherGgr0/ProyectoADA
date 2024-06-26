/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafo.Dijkstra;

/*
 * Representación de un grafo no dirigido ponderado utilizando una estructura de mapa de adyacencia.
 */
import java.util.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Clase que representa un grafo no dirigido ponderado utilizando una estructura de mapa de adyacencia.
 */
public class Grafo {

    private Map<String, Map<String, Integer>> grafo; // Mapa de adyacencia para almacenar vértices y sus conexiones con pesos

    /**
     * Constructor que inicializa un nuevo grafo vacío.
     */
    public Grafo() {
        grafo = new HashMap<>();
    }

    /**
     * Agrega un nuevo vértice al grafo si no existe previamente.
     * @param vertice Nombre del vértice a agregar.
     */
    public void agregarVertice(String vertice) {
        if (!grafo.containsKey(vertice)) {
            grafo.put(vertice, new HashMap<>());
        }
    }

    /**
     * Agrega una arista no dirigida entre dos vértices con un peso específico.
     * @param origen Vértice de origen de la arista.
     * @param destino Vértice de destino de la arista.
     * @param peso Peso de la arista que conecta los vértices.
     * @throws IllegalArgumentException Si alguno de los vértices no existe en el grafo.
     */
    public void agregarArista(String origen, String destino, int peso) {
        if (!grafo.containsKey(origen) || !grafo.containsKey(destino)) {
            throw new IllegalArgumentException("Vértices no existentes");
        }
        grafo.get(origen).put(destino, peso);
        grafo.get(destino).put(origen, peso); // Considerando que el grafo es no dirigido
    }

    /**
     * Elimina un vértice y todas sus aristas adyacentes del grafo.
     * @param vertice Vértice a eliminar.
     * @throws IllegalArgumentException Si el vértice especificado no existe en el grafo.
     */
    public void eliminarVertice(String vertice) {
        if (!grafo.containsKey(vertice)) {
            throw new IllegalArgumentException("Vértice no existente");
        }
        grafo.remove(vertice);
        for (Map<String, Integer> adyacentes : grafo.values()) {
            adyacentes.remove(vertice); // Eliminar conexiones hacia el vértice eliminado
        }
    }

    /**
     * Elimina la arista entre dos vértices especificados.
     * Si los vértices no están conectados, no realiza ninguna acción.
     * @param origen Vértice de origen de la arista a eliminar.
     * @param destino Vértice de destino de la arista a eliminar.
     */
    public void eliminarArista(String origen, String destino) {
        if (grafo.containsKey(origen)) {
            grafo.get(origen).remove(destino);
        }
        if (grafo.containsKey(destino)) {
            grafo.get(destino).remove(origen);
        }
    }

    /**
     * Obtiene un mapa que contiene los vértices adyacentes a un vértice dado y los pesos de las aristas que los conectan.
     * @param vertice Vértice del cual se obtendrán los adyacentes.
     * @return Mapa de vértices adyacentes y pesos de las aristas.
     */
    public Map<String, Integer> obtenerAdyacentes(String vertice) {
        return grafo.getOrDefault(vertice, new HashMap<>());
    }

    /**
     * Obtiene el conjunto de todos los vértices presentes en el grafo.
     * @return Conjunto de vértices del grafo.
     */
    public Set<String> obtenerVertices() {
        return grafo.keySet();
    }

    /**
     * Verifica si un vértice dado existe en el grafo.
     * @param vertice Vértice a verificar.
     * @return true si el vértice existe en el grafo, false en caso contrario.
     */
    public boolean existeVertice(String vertice) {
        return grafo.containsKey(vertice);
    }

    /**
     * Obtiene el peso de la arista que conecta dos vértices especificados.
     * @param origen Vértice de origen de la arista.
     * @param destino Vértice de destino de la arista.
     * @return Peso de la arista, o null si los vértices no están conectados o no existen.
     */
    public Integer obtenerPesoArista(String origen, String destino) {
        if (grafo.containsKey(origen) && grafo.get(origen).containsKey(destino)) {
            return grafo.get(origen).get(destino);
        }
        return null;
    }
}

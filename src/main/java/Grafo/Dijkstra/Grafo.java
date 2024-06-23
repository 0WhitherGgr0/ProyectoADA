/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafo.Dijkstra;

import java.util.*;

public class Grafo {
    private Map<String, Map<String, Integer>> grafo;

    public Grafo() {
        grafo = new HashMap<>();
    }

    public void agregarVertice(String vertice) {
        if (!grafo.containsKey(vertice)) {
            grafo.put(vertice, new HashMap<>());
        }
    }

    public void agregarArista(String origen, String destino, int peso) {
        if (!grafo.containsKey(origen) || !grafo.containsKey(destino)) {
            throw new IllegalArgumentException("Vértices no existentes");
        }
        grafo.get(origen).put(destino, peso);
        grafo.get(destino).put(origen, peso); // Considerando que el grafo es no dirigido
    }

    public void eliminarVertice(String vertice) {
        if (!grafo.containsKey(vertice)) {
            throw new IllegalArgumentException("Vértice no existente");
        }
        grafo.remove(vertice);
        for (Map<String, Integer> adyacentes : grafo.values()) {
            adyacentes.remove(vertice);
        }
    }

    public void eliminarArista(String origen, String destino) {
        if (grafo.containsKey(origen)) {
            grafo.get(origen).remove(destino);
        }
        if (grafo.containsKey(destino)) {
            grafo.get(destino).remove(origen);
        }
    }

    public Map<String, Integer> obtenerAdyacentes(String vertice) {
        return grafo.getOrDefault(vertice, new HashMap<>());
    }

    public Set<String> obtenerVertices() {
        return grafo.keySet();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafo.Dijkstra;

import java.util.*;
/*
 * Implementación del algoritmo de Dijkstra para encontrar caminos mínimos en un grafo ponderado.
 */
import java.util.*;

/**
 * Clase que contiene métodos estáticos para ejecutar el algoritmo de Dijkstra y calcular la distancia total de un recorrido en un grafo.
 */
public class Dijkstra {

    /**
     * Ejecuta el algoritmo de Dijkstra para encontrar las distancias mínimas desde un vértice de inicio en un grafo ponderado.
     * @param grafo Grafo en el que se ejecutará el algoritmo.
     * @param inicio Vértice de inicio para calcular las distancias mínimas.
     * @return Mapa que contiene los vértices y sus distancias mínimas desde el vértice de inicio.
     */
    public static Map<String, Integer> ejecutarDijkstra(Grafo grafo, String inicio) {
        Map<String, Integer> distancias = new HashMap<>();  // Mapa para almacenar las distancias mínimas desde el inicio
        PriorityQueue<Map.Entry<String, Integer>> colaPrioridad = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

        // Inicialización de distancias con valor infinito
        for (String vertice : grafo.obtenerVertices()) {
            distancias.put(vertice, Integer.MAX_VALUE);
        }
        distancias.put(inicio, 0);  // Distancia al propio inicio es 0
        colaPrioridad.add(new AbstractMap.SimpleEntry<>(inicio, 0));

        // Algoritmo de Dijkstra
        while (!colaPrioridad.isEmpty()) {
            String verticeActual = colaPrioridad.poll().getKey();
            int distanciaActual = distancias.get(verticeActual);

            // Iterar sobre los vértices adyacentes
            for (Map.Entry<String, Integer> adyacente : grafo.obtenerAdyacentes(verticeActual).entrySet()) {
                String verticeAdyacente = adyacente.getKey();
                int pesoArista = adyacente.getValue();
                int distanciaNueva = distanciaActual + pesoArista;

                // Si se encuentra una distancia más corta, actualizar y agregar a la cola de prioridad
                if (distanciaNueva < distancias.get(verticeAdyacente)) {
                    distancias.put(verticeAdyacente, distanciaNueva);
                    colaPrioridad.add(new AbstractMap.SimpleEntry<>(verticeAdyacente, distanciaNueva));
                }
            }
        }

        return distancias;
    }

    /**
     * Calcula la distancia total de un recorrido especificado por una secuencia de vértices en un grafo.
     * Utiliza el algoritmo de Dijkstra para calcular la distancia mínima entre cada par de vértices consecutivos en la secuencia.
     * @param grafo Grafo en el que se calculará la distancia total.
     * @param secuencia Secuencia de vértices que define el recorrido.
     * @return Distancia total del recorrido especificado.
     */
    public static int calcularDistanciaTotal(Grafo grafo, List<String> secuencia) {
        int distanciaTotal = 0;
        for (int i = 0; i < secuencia.size() - 1; i++) {
            String inicio = secuencia.get(i);
            String fin = secuencia.get(i + 1);
            Map<String, Integer> distancias = ejecutarDijkstra(grafo, inicio);
            distanciaTotal += distancias.get(fin);
        }
        return distanciaTotal;
    }

    /**
     * Reconstruye y muestra el camino óptimo desde el vértice de inicio hasta el vértice de destino utilizando un mapa de predecesores.
     * @param inicio Vértice de inicio del camino.
     * @param destino Vértice de destino del camino.
     * @param predecesores Mapa que contiene los predecesores de cada vértice en el camino óptimo.
     */
    private static void reconstruirCamino(String inicio, String destino, Map<String, String> predecesores) {
        List<String> camino = new ArrayList<>();
        String actual = destino;
        camino.add(actual);
        while (!actual.equals(inicio)) {
            actual = predecesores.get(actual);
            camino.add(actual);
        }
        Collections.reverse(camino);
        System.out.print("Camino óptimo de " + inicio + " a " + destino + ": ");
        for (int i = 0; i < camino.size() - 1; i++) {
            System.out.print("V\"" + camino.get(i) + "\"->");
        }
        System.out.println("V\"" + destino + "\"");
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafo.Dijkstra;

import java.util.*;

public class Dijkstra {

    public static Map<String, Integer> ejecutarDijkstra(Grafo grafo, String inicio) {
        Map<String, Integer> distancias = new HashMap<>();
        PriorityQueue<Map.Entry<String, Integer>> colaPrioridad = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

        // Inicialización de distancias
        for (String vertice : grafo.obtenerVertices()) {
            distancias.put(vertice, Integer.MAX_VALUE);
        }
        distancias.put(inicio, 0);
        colaPrioridad.add(new AbstractMap.SimpleEntry<>(inicio, 0));

        while (!colaPrioridad.isEmpty()) {
            String verticeActual = colaPrioridad.poll().getKey();
            int distanciaActual = distancias.get(verticeActual);

            for (Map.Entry<String, Integer> adyacente : grafo.obtenerAdyacentes(verticeActual).entrySet()) {
                String verticeAdyacente = adyacente.getKey();
                int pesoArista = adyacente.getValue();
                int distanciaNueva = distanciaActual + pesoArista;

                if (distanciaNueva < distancias.get(verticeAdyacente)) {
                    distancias.put(verticeAdyacente, distanciaNueva);
                    colaPrioridad.add(new AbstractMap.SimpleEntry<>(verticeAdyacente, distanciaNueva));
                }
            }
        }

        return distancias;
    }
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
}

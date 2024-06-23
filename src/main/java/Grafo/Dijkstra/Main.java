/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafo.Dijkstra;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        // Definir el grafo igual al ejemplo en Python
        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");
        grafo.agregarVertice("D");
        grafo.agregarVertice("E");
        grafo.agregarVertice("F");
        grafo.agregarVertice("G");
        grafo.agregarVertice("H");
        grafo.agregarVertice("I");
        grafo.agregarVertice("J");
        grafo.agregarVertice("K");
        grafo.agregarVertice("L");
        grafo.agregarVertice("M");
        grafo.agregarVertice("N");
        grafo.agregarVertice("O");
        grafo.agregarVertice("P");

        grafo.agregarArista("A", "B", 2);
        grafo.agregarArista("A", "C", 5);
        grafo.agregarArista("A", "D", 1);
        grafo.agregarArista("A", "E", 4);
        grafo.agregarArista("B", "C", 3);
        grafo.agregarArista("B", "F", 7);
        grafo.agregarArista("C", "D", 4);
        grafo.agregarArista("C", "G", 1);
        grafo.agregarArista("D", "H", 2);
        grafo.agregarArista("E", "F", 3);
        grafo.agregarArista("E", "I", 6);
        grafo.agregarArista("F", "G", 2);
        grafo.agregarArista("F", "J", 5);
        grafo.agregarArista("G", "H", 3);
        grafo.agregarArista("G", "K", 4);
        grafo.agregarArista("H", "L", 1);
        grafo.agregarArista("I", "J", 2);
        grafo.agregarArista("I", "M", 4);
        grafo.agregarArista("J", "K", 3);
        grafo.agregarArista("J", "N", 7);
        grafo.agregarArista("K", "L", 2);
        grafo.agregarArista("K", "O", 6);
        grafo.agregarArista("L", "P", 5);
        grafo.agregarArista("M", "N", 1);
        grafo.agregarArista("N", "O", 3);
        grafo.agregarArista("O", "P", 2);

        // Secuencia de nodos a visitar
        List<String> secuencia = Arrays.asList("A", "C", "G", "K", "O", "P", "L", "H", "D", "A");

        int distanciaTotal = calcularDistanciaTotal(grafo, secuencia);
        System.out.println("Distancia total del recorrido: " + distanciaTotal);
    }

    public static int calcularDistanciaTotal(Grafo grafo, List<String> secuencia) {
        int distanciaTotal = 0;
        for (int i = 0; i < secuencia.size() - 1; i++) {
            String inicio = secuencia.get(i);
            String fin = secuencia.get(i + 1);
            Map<String, Integer> distancias = Dijkstra.ejecutarDijkstra(grafo, inicio);
            distanciaTotal += distancias.get(fin);
        }
        return distanciaTotal;
    }
}

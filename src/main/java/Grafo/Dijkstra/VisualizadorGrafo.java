/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafo.Dijkstra;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class VisualizadorGrafo {

    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        // Definición del grafo con los vértices y aristas del ejemplo
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
        List<String> sequence = List.of("A", "C", "G", "K", "O", "P", "L", "H", "D", "A");

        // Calcular la distancia total
        int totalDistance = Dijkstra.calcularDistanciaTotal(grafo, sequence);
        System.out.println("Distancia total del recorrido: " + totalDistance);

        // Crear la ventana de visualización
        JFrame frame = new JFrame("Visualización de Grafo y Ruta Mínima");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 800);
        frame.setLayout(new BorderLayout());

        // Panel personalizado para dibujar el grafo completo
        GrafoPanel grafoPanel = new GrafoPanel(grafo, List.of()); // Lista vacía para no dibujar recorrido

        // Panel personalizado para dibujar solo el recorrido
        RecorridoPanel recorridoPanel = new RecorridoPanel(grafo, sequence);

        // Crear un JSplitPane para dividir la ventana en dos paneles
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, grafoPanel, recorridoPanel);
        splitPane.setDividerLocation(800); // Posición inicial del divisor

        frame.add(splitPane, BorderLayout.CENTER);

        // Panel para agregar botones de control
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        JButton addButton = new JButton("Agregar Nodo");
        JButton removeButton = new JButton("Eliminar Nodo");
        JButton refreshButton = new JButton("Actualizar Grafo");

        controlPanel.add(addButton);
        controlPanel.add(removeButton);
        controlPanel.add(refreshButton);

        frame.add(controlPanel, BorderLayout.SOUTH);

        // Agregar funcionalidad a los botones
        addButton.addActionListener(e -> {
            String nodo = JOptionPane.showInputDialog(frame, "Ingrese el nombre del nuevo nodo:");
            if (nodo != null && !nodo.isEmpty()) {
                grafo.agregarVertice(nodo);
                grafoPanel.repaint();
                recorridoPanel.repaint();
            }
        });

        removeButton.addActionListener(e -> {
            String nodo = JOptionPane.showInputDialog(frame, "Ingrese el nombre del nodo a eliminar:");
            if (nodo != null && !nodo.isEmpty()) {
                grafo.eliminarVertice(nodo);
                grafoPanel.repaint();
                recorridoPanel.repaint();
            }
        });

        refreshButton.addActionListener(e -> {
            grafoPanel.repaint();
            recorridoPanel.repaint();
        });

        frame.setVisible(true);
    }
}
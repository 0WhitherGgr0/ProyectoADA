/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafo.Dijkstra;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Panel personalizado para visualizar un recorrido específico en un grafo utilizando gráficos en Java Swing.
 */
class RecorridoPanel extends JPanel {

    private Grafo grafo; // Grafo sobre el cual se visualiza el recorrido
    private List<String> sequence; // Secuencia de vértices que representan el recorrido
    private Map<String, Integer> vertexPositions; // Mapa para almacenar las posiciones de los vértices en el panel
    private final int radius = 40; // Radio del círculo del nodo
    private final int padding = 50; // Espacio de padding del panel

    /**
     * Constructor del panel de recorrido.
     * @param grafo Grafo sobre el cual se visualiza el recorrido.
     * @param sequence Secuencia de vértices que representan el recorrido.
     */
    public RecorridoPanel(Grafo grafo, List<String> sequence) {
        this.grafo = grafo;
        this.sequence = sequence;
        this.vertexPositions = new HashMap<>();
    }

    /**
     * Método sobrescrito para dibujar el recorrido en el panel.
     * @param g Objeto Graphics utilizado para dibujar componentes.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Mejorar la calidad del dibujo

        int numVertices = grafo.obtenerVertices().size();
        Point[] puntos = new Point[numVertices];

        // Calcular posiciones de los nodos en un círculo
        int width = getWidth() - 2 * padding;
        int height = getHeight() - 2 * padding;
        int centerX = padding + width / 2;
        int centerY = padding + height / 2;
        int radio = Math.min(width, height) / 2;
        int i = 0;

        for (String vertex : grafo.obtenerVertices()) {
            double angle = 2 * Math.PI * i / numVertices;
            int x = centerX + (int) (radio * Math.cos(angle));
            int y = centerY + (int) (radio * Math.sin(angle));
            puntos[i] = new Point(x, y);
            vertexPositions.put(vertex, i);
            i++;
        }

        // Dibujar aristas de la ruta mínima
        g2d.setStroke(new BasicStroke(3)); // Grosor de la línea de las aristas de la ruta mínima
        g2d.setColor(Color.BLACK);
        for (int j = 0; j < sequence.size() - 1; j++) {
            String start = sequence.get(j);
            String end = sequence.get(j + 1);
            int startIndex = vertexPositions.get(start);
            int endIndex = vertexPositions.get(end);
            g2d.drawLine(puntos[startIndex].x, puntos[startIndex].y, puntos[endIndex].x, puntos[endIndex].y);
        }

        // Dibujar nodos del recorrido
        g2d.setColor(Color.RED);
        g2d.setFont(new Font("Arial", Font.BOLD, 16)); // Tamaño de la fuente para los nombres de los vértices
        FontMetrics fm = g2d.getFontMetrics();
        for (String vertex : sequence) {
            int vertexIndex = vertexPositions.get(vertex);
            g2d.fillOval(puntos[vertexIndex].x - radius / 2, puntos[vertexIndex].y - radius / 2, radius, radius);
            String vertice = vertex;
            int textWidth = fm.stringWidth(vertice);
            int textHeight = fm.getHeight();
            g2d.setColor(Color.BLACK);
            g2d.drawString(vertice, puntos[vertexIndex].x - textWidth / 2, puntos[vertexIndex].y + textHeight / 4);
            g2d.setColor(Color.RED);
        }
    }

    /**
     * Método para actualizar el recorrido visualizado en el panel.
     * @param nuevoRecorrido Nueva secuencia de vértices que representa el nuevo recorrido.
     */
    public void actualizarRecorrido(List<String> nuevoRecorrido) {
        this.sequence = nuevoRecorrido;
        repaint(); // Vuelve a dibujar el panel con el nuevo recorrido
    }
}

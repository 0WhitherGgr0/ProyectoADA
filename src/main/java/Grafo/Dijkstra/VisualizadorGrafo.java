/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafo.Dijkstra;

import Grafo.Clientes.*;

    import javax.swing.*;
    import java.awt.*;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.stream.Collectors;

    /**
     * Clase principal que implementa un visualizador de grafo interactivo utilizando Java Swing.
     */
    public class VisualizadorGrafo {

        private static Nodo nodoBase; // Nodo base del recorrido
        private static List<Nodo> nodosClientes; // Lista de nodos clientes
        private static List<Nodo> nodos; // Todos los nodos del grafo
        private static Grafo grafo; // Grafo representado
        private static RecorridoPanel recorridoPanel; // Panel para visualizar el recorrido
        private static GrafoPanel grafoPanel; // Panel para visualizar el grafo completo

        /**
         * Método principal que inicia la aplicación de visualización del grafo.
         * @param args Argumentos de línea de comandos (no utilizado en este caso).
         */
        public static void main(String[] args) {
            grafo = new Grafo();
            nodos = new ArrayList<>();

            // Definición del grafo con los vértices y aristas del ejemplo
            agregarVerticesYAristas();

            // Inicializar nodo base y nodos clientes
            nodoBase = new Nodo("A", true);
            nodosClientes = List.of(
                    new Nodo("C", false),
                    new Nodo("G", false),
                    new Nodo("K", false),
                    new Nodo("O", false),
                    new Nodo("P", false),
                    new Nodo("L", false),
                    new Nodo("H", false),
                    new Nodo("D", false)
            );

            // Añadir nodos base y clientes a la lista de nodos
            nodos.add(nodoBase);
            nodos.addAll(nodosClientes);

            // Calcular la distancia total y crear la ventana de visualización
            crearVentana();

            // Calcular y mostrar la distancia total
            calcularYMostrarDistanciaTotal();
        }

        /**
         * Agrega los vértices y aristas al grafo según el ejemplo dado.
         */
        private static void agregarVerticesYAristas() {
            // Agregar vértices
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

            // Agregar aristas
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
        }

        /**
         * Crea la ventana de visualización del grafo y el recorrido.
         */
        private static void crearVentana() {
            JFrame frame = new JFrame("Visualización de Grafo y Ruta Mínima");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1600, 800);
            frame.setLayout(new BorderLayout());

            // Panel personalizado para dibujar el grafo completo
            grafoPanel = new GrafoPanel(grafo, List.of());

            // Panel personalizado para dibujar solo el recorrido
            recorridoPanel = new RecorridoPanel(grafo, obtenerSecuencia());

            // Crear un JSplitPane para dividir la ventana en dos paneles
            JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, grafoPanel, recorridoPanel);
            splitPane.setDividerLocation(800);

            frame.add(splitPane, BorderLayout.CENTER);

            // Panel para agregar botones de control
            JPanel controlPanel = new JPanel();
            controlPanel.setLayout(new FlowLayout());

            JButton addButton = new JButton("Agregar Nodo");
            JButton removeButton = new JButton("Eliminar Nodo");
            JButton addEdgeButton = new JButton("Agregar Arco");
            JButton refreshButton = new JButton("Actualizar Grafo");
            JButton setBaseButton = new JButton("Establecer Nodo Base");
            JButton addClientButton = new JButton("Agregar Cliente");
            JButton removeClientButton = new JButton("Eliminar Cliente");

            controlPanel.add(addButton);
            controlPanel.add(removeButton);
            controlPanel.add(addEdgeButton);
            controlPanel.add(refreshButton);
            controlPanel.add(setBaseButton);
            controlPanel.add(addClientButton);
            controlPanel.add(removeClientButton);

            frame.add(controlPanel, BorderLayout.SOUTH);

            // Agregar funcionalidad a los botones
            addButton.addActionListener(e -> agregarNodo());
            removeButton.addActionListener(e -> eliminarNodo());
            addEdgeButton.addActionListener(e -> agregarArco());
            refreshButton.addActionListener(e -> actualizarGrafo());
            setBaseButton.addActionListener(e -> establecerNodoBase());
            addClientButton.addActionListener(e -> agregarCliente());
            removeClientButton.addActionListener(e -> eliminarCliente());

            frame.setVisible(true);
        }

        /**
         * Método para agregar un nuevo nodo al grafo.
         */
        private static void agregarNodo() {
            String nodo = JOptionPane.showInputDialog(null, "Ingrese el nombre del nuevo nodo:");
            if (nodo != null && !nodo.isEmpty()) {
                if (grafo.existeVertice(nodo)) {
                    JOptionPane.showMessageDialog(null, "Nodo ya existente.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    grafo.agregarVertice(nodo);
                    grafoPanel.repaint();
                    recorridoPanel.repaint();
                }
            }
        }

        /**
         * Método para eliminar un nodo del grafo.
         */
        private static void eliminarNodo() {
            String nodo = JOptionPane.showInputDialog(null, "Ingrese el nombre del nodo a eliminar:");
            if (nodo != null && !nodo.isEmpty()) {
                grafo.eliminarVertice(nodo);
                grafoPanel.repaint();
                recorridoPanel.repaint();
            }
        }

        /**
         * Método para agregar una nueva arista (o arco) al grafo.
         */
        private static void agregarArco() {
            JTextField origenField = new JTextField();
            JTextField destinoField = new JTextField();
            JTextField pesoField = new JTextField();
            Object[] message = {
                "Nodo Origen:", origenField,
                "Nodo Destino:", destinoField,
                "Peso:", pesoField
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Agregar Arco", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String origen = origenField.getText();
                String destino = destinoField.getText();
                String pesoStr = pesoField.getText();

                if (!grafo.existeVertice(origen)) {
                    JOptionPane.showMessageDialog(null, "Nodo origen no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!grafo.existeVertice(destino)) {
                    JOptionPane.showMessageDialog(null, "Nodo destino no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        int peso = Integer.parseInt(pesoStr);
                        if (peso <= 0) {
                            JOptionPane.showMessageDialog(null, "Peso debe ser un número positivo mayor que cero.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            grafo.agregarArista(origen, destino, peso);
                            grafoPanel.repaint();
                            recorridoPanel.repaint();
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Peso inválido. Debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }

        /**
         * Método para actualizar el grafo y mostrar el recorrido actualizado.
         */
        private static void actualizarGrafo() {
            calcularYMostrarDistanciaTotal();
            recorridoPanel.actualizarRecorrido(obtenerSecuencia());
            grafoPanel.repaint();
            recorridoPanel.repaint();
        }

        /**
         * Obtiene la secuencia de nodos que representa el recorrido.
         * @return Lista de nombres de nodos que forman el recorrido.
         */
        private static List<String> obtenerSecuencia() {
            List<String> secuencia = new ArrayList<>();
            secuencia.add(nodoBase.getNombre());
            secuencia.addAll(nodosClientes.stream().map(Nodo::getNombre).collect(Collectors.toList()));
            secuencia.add(nodoBase.getNombre());
            return secuencia;
        }

        /**
         * Calcula y muestra la distancia total del recorrido actual.
         */
        private static void calcularYMostrarDistanciaTotal() {
            List<String> secuencia = obtenerSecuencia();
            int totalDistance = Dijkstra.calcularDistanciaTotal(grafo, secuencia);
            System.out.println("Distancia total del recorrido: " + totalDistance);

            // Imprimir por consola el recorrido en el formato V"nombre"->V"Destino"
            for (int i = 0; i < secuencia.size() - 1; i++) {
                String origen = secuencia.get(i);
                String destino = secuencia.get(i + 1);
                System.out.println("V\"" + origen + "\"->V\"" + destino + "\"");
            }
        }

        /**
         * Establece el nodo base del recorrido según la entrada del usuario.
         */
        private static void establecerNodoBase() {
            String nodo = JOptionPane.showInputDialog(null, "Ingrese el nombre del nodo base:");
            if (nodo != null && !nodo.isEmpty() && grafo.existeVertice(nodo)) {
                nodoBase.setEsBase(false);
                nodoBase = new Nodo(nodo, true);
                nodosClientes = nodos.stream().filter(n -> !n.esBase()).collect(Collectors.toList());
                actualizarGrafo();
            } else {
                JOptionPane.showMessageDialog(null, "Nodo no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        /**
         * Agrega un nuevo cliente al recorrido según la entrada del usuario.
         */
        private static void agregarCliente() {
            String nodo = JOptionPane.showInputDialog(null, "Ingrese el nombre del nuevo cliente:");
            if (nodo != null && !nodo.isEmpty() && grafo.existeVertice(nodo)) {
                nodosClientes.add(new Nodo(nodo, false));
                actualizarGrafo();
            } else {
                JOptionPane.showMessageDialog(null, "Nodo no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        /**
         * Elimina un cliente del recorrido según la entrada del usuario.
         */
        private static void eliminarCliente() {
            String nodo = JOptionPane.showInputDialog(null, "Ingrese el nombre del cliente a eliminar:");
            if (nodo != null && !nodo.isEmpty() && grafo.existeVertice(nodo)) {
                nodosClientes = nodosClientes.stream().filter(n -> !n.getNombre().equals(nodo)).collect(Collectors.toList());
                actualizarGrafo();
            } else {
                JOptionPane.showMessageDialog(null, "Nodo no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

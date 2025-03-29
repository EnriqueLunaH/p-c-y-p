package Practica2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

class Pelota implements Runnable { // Implementar Runnable para usar hilos
    private Graphics g;
    private int x = 7, xCambio = 7;
    private int y = 0, yCambio = 2;
    private int diametro = 10;
    private int rectIzqX = 0, rectDerX = 100;
    private int rectSupY = 0, rectInfY = 100;
    private boolean running = true; // Control para detener el hilo

    public Pelota(Graphics graficos) {
        g = graficos;
    }

    public void detener() {
        running = false; // Método para detener la pelota si es necesario
    }

    @Override
    public void run() {
        g.drawRect(rectIzqX, rectSupY, rectDerX - rectIzqX + 10, rectInfY - rectSupY + 10);

        for (int n = 1; n < 1000 && running; n++) { // Se ejecuta mientras "running" sea true
            g.setColor(Color.white);
            g.fillOval(x, y, diametro, diametro);

            if (x + xCambio <= rectIzqX || x + xCambio >= rectDerX) {
                xCambio = -xCambio;
            }
            if (y + yCambio <= rectSupY || y + yCambio >= rectInfY) {
                yCambio = -yCambio;
            }

            x += xCambio;
            y += yCambio;
            g.setColor(Color.red);
            g.fillOval(x, y, diametro, diametro);

            try {
                Thread.sleep(50); // Pequeña pausa para ver el movimiento
            } catch (InterruptedException e) {
                System.err.println("Excepcion de inactividad");
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class hiloPelota extends JPanel implements ActionListener {
    private JButton iniciar;
    private JButton agregarPelota; // Nuevo botón para agregar pelota
    private JButton detener; // Nuevo botón para detener pelotas
    private ArrayList<Pelota> pelotas; // Lista para manejar múltiples pelotas
    private ArrayList<Thread> hilosPelotas; // Lista para manejar múltiples hilos de pelotas

    public hiloPelota() {
        iniciar = new JButton("Iniciar");
        agregarPelota = new JButton("Agregar Pelota"); // Inicializar botón
        detener = new JButton("Detener"); // Inicializar botón
        add(iniciar);
        add(agregarPelota); // Agregar botón al panel
        add(detener); // Agregar botón al panel
        iniciar.addActionListener(this);
        agregarPelota.addActionListener(this); // Añadir ActionListener
        detener.addActionListener(this); // Añadir ActionListener
        pelotas = new ArrayList<>(); // Inicializar lista de pelotas
        hilosPelotas = new ArrayList<>(); // Inicializar lista de hilos
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == iniciar) {
            Graphics g = getGraphics();
            Pelota pelota = new Pelota(g);
            pelotas.add(pelota); // Agregar pelota a la lista
            Thread hiloPelota = new Thread(pelota); // Crear hilo con la pelota
            hilosPelotas.add(hiloPelota); // Agregar hilo a la lista
            hiloPelota.start(); // Iniciar hilo
        } else if (event.getSource() == agregarPelota) {
            Graphics g = getGraphics();
            Pelota pelota = new Pelota(g);
            pelotas.add(pelota); // Agregar pelota a la lista
            Thread hiloPelota = new Thread(pelota); // Crear hilo con la nueva pelota
            hilosPelotas.add(hiloPelota); // Agregar hilo a la lista
            hiloPelota.start(); // Iniciar hilo
        } else if (event.getSource() == detener) {
            for (Pelota pelota : pelotas) {
                pelota.detener(); // Detener cada pelota
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hilo Pelota");
        hiloPelota panel = new hiloPelota();
        frame.add(panel);
        frame.setSize(200, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

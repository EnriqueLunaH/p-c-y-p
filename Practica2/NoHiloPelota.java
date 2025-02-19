package Practica2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Pelota {
    private int x = 7, xCambio = 7;
    private int y = 0, yCambio = 2;
    private int diametro = 10;
    private int rectIzqX = 0, rectDerX = 100;
    private int rectSupY = 0, rectInfY = 100;

    public void mover() {
        // Verifica colisiones y cambia la dirección
        if (x + xCambio <= rectIzqX || x + xCambio >= rectDerX) {
            xCambio = -xCambio;
        }
        if (y + yCambio <= rectSupY || y + yCambio >= rectInfY) {
            yCambio = -yCambio;
        }

        // Actualiza posición
        x += xCambio;
        y += yCambio;
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(x, y, diametro, diametro);
    }
}

public class NoHiloPelota extends JPanel implements ActionListener {
    private JButton iniciar;
    private Timer timer; // Usaremos un Timer en lugar de un Thread
    private Pelota pelota;

    public NoHiloPelota() {
        iniciar = new JButton("Iniciar");
        add(iniciar);
        iniciar.addActionListener(this);
        pelota = new Pelota();
        timer = new Timer(50, e -> {
            pelota.mover();
            repaint(); // Redibuja la pelota con la nueva posición
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(0, 0, 110, 110); // Dibuja el área de rebote
        pelota.dibujar(g); // Dibuja la pelota
    }

    public void actionPerformed(ActionEvent event) {
        if (!timer.isRunning()) {
            timer.start(); // Iniciar el Timer cuando se presiona el botón
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hilo Pelota");
        NoHiloPelota panel = new NoHiloPelota();
        frame.add(panel);
        frame.setSize(200, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

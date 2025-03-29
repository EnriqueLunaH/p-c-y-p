package Practica2;

import javax.swing.*;
import java.awt.*;

class Jardin {
    private final JTextArea textArea;

    public Jardin(JTextArea textArea) {
        this.textArea = textArea;
    }

    public synchronized void sembrar(String nombre) {
        for (int i = 1; i <= 10; i++) {
            textArea.append(nombre + " está sembrando el arbol " + i + "\n");

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            textArea.append(nombre + " ha terminado de sembrar el arbol " + i + "\n");
        }
    }
}

class Hermano extends Thread {
    private final Jardin jardin;

    public Hermano(Jardin jardin, String nombre) {
        super(nombre);
        this.jardin = jardin;
    }

    @Override
    public void run() {
        jardin.sembrar(getName());
    }
}

public class HermanosV1ThreadGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hermanos Sembrando Arboles");
        JTextArea textArea = new JTextArea(20, 40);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        Jardin jardin = new Jardin(textArea);
        Hermano hermano1 = new Hermano(jardin, "Hermano 1");
        Hermano hermano2 = new Hermano(jardin, "Hermano 2");

        hermano1.start();
        try {
            hermano1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        hermano2.start();
        try {
            hermano2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        textArea.append("Ambos hermanos han terminado de sembrar los arboles\n");
    }
}
package Practica2;

import javax.swing.*;
import java.awt.*;

class Jardin {
    private boolean turnoHermano1 = true;
    private final JTextArea textArea;

    public Jardin(JTextArea textArea) {
        this.textArea = textArea;
    }

    public synchronized void sembrar(String nombre, boolean esHermano1) {
        for (int i = 1; i <= 10; i++) {
            synchronized (this) {
                while (turnoHermano1 != esHermano1) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                textArea.append(nombre + " esta sembrando el árbol " + i + "\n");

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                textArea.append(nombre + " ha terminado de sembrar el arbol " + i + "\n");

                turnoHermano1 = !esHermano1;
                notifyAll();
            }
        }
    }
}

class Hermano extends Thread {
    private final Jardin jardin;
    private final boolean esHermano1;

    public Hermano(Jardin jardin, String nombre, boolean esHermano1) {
        super(nombre);
        this.jardin = jardin;
        this.esHermano1 = esHermano1;
    }

    @Override
    public void run() {
        jardin.sembrar(getName(), esHermano1);
    }
}

public class HermanosV2RunnableGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hermanos Sembrando arboles");
        JTextArea textArea = new JTextArea(20, 40);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        Jardin jardin = new Jardin(textArea);
        Hermano hermano1 = new Hermano(jardin, "Hermano 1", true);
        Hermano hermano2 = new Hermano(jardin, "Hermano 2", false);

        hermano1.start();
        hermano2.start();

        try {
            hermano1.join();
            hermano2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        textArea.append("Ambos hermanos han terminado de sembrar los arboles\n");
    }
}

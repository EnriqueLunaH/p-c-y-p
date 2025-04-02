package Practica3.GrafoAG_GUI;

import javax.swing.*;

public class Main extends JFrame {

    JTextArea area = new JTextArea(10, 40);
    JButton iniciar = new JButton("Iniciar");

    public Main() {
        setTitle("Grafo A-G");
        setLayout(null);
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);
        scroll.setBounds(20, 20, 460, 200);
        add(scroll);

        iniciar.setBounds(180, 230, 140, 30);
        add(iniciar);

        iniciar.addActionListener(e -> ejecutar());

        setSize(500, 320);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void ejecutar() {
        new ProcesoA().start();
        new ProcesoB().start();
        new ProcesoC().start();
        new ProcesoD().start();
        new ProcesoE().start();
        new ProcesoF().start();
        new ProcesoG().start();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
                for (String s : Monitor.aviso) {
                    area.append(s + "\n");
                }
            } catch (InterruptedException ex) {}
        }).start();
    }

    public static void main(String[] args) {
        new Main();
    }
}
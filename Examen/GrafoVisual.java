package Examen;

import javax.swing.*;
import java.awt.*;

public class GrafoVisual extends JFrame {
    private final GrafoPanel panel;
    
    public GrafoVisual() {
        setTitle("Grafo de Procesos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        panel = new GrafoPanel();
        add(panel);
    }

    public class GrafoPanel extends JPanel {
        private boolean[] completed = new boolean[6];

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            // Dibujar conexiones según procesos completados
            if (completed[2]) drawLine(g, 100, 100, 200, 200); // S1 → S3
            if (completed[3]) drawLine(g, 300, 100, 400, 200); // S2 → S4
            if (completed[4]) drawLine(g, 200, 200, 400, 200); // S3 → S4
            if (completed[4]) drawLine(g, 200, 200, 200, 300); // S3 → S5
            if (completed[5]) drawLine(g, 400, 200, 400, 300); // S4 → S6
            if (completed[5]) drawLine(g, 200, 300, 400, 300); // S5 → S6
            
            // Dibujar nodos completados
            if (completed[0]) drawCircle(g, 100, 100, "S1");
            if (completed[1]) drawCircle(g, 300, 100, "S2");
            if (completed[2]) drawCircle(g, 200, 200, "S3");
            if (completed[3]) drawCircle(g, 400, 200, "S4");
            if (completed[4]) drawCircle(g, 200, 300, "S5");
            if (completed[5]) drawCircle(g, 400, 300, "S6");
        }

        private void drawCircle(Graphics g, int x, int y, String text) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillOval(x - 30, y - 30, 60, 60);
            g.setColor(Color.BLACK);
            g.drawOval(x - 30, y - 30, 60, 60);
            g.drawString(text, x - 10, y + 5);
        }

        private void drawLine(Graphics g, int x1, int y1, int x2, int y2) {
            g.setColor(Color.BLACK);
            g.drawLine(x1, y1, x2, y2);
        }

        public synchronized void markCompleted(int index) {
            completed[index] = true;
            repaint();
        }
    }

    private void startProcesses() {
        S1 s1 = new S1(panel, 0);
        S2 s2 = new S2(panel, 1);
        Thread t3 = new Thread(new S3(panel, s1, 2));
        Thread t4 = new Thread(new S4(panel, t3, s2, 3));
        Thread t5 = new Thread(new S5(panel, t3, 4));
        Thread t6 = new Thread(new S6(panel, t4, t5, 5));

        s1.start();
        s2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GrafoVisual frame = new GrafoVisual();
            frame.setVisible(true);
            frame.startProcesses();
        });
    }
}

class S1 extends Thread {
    private GrafoVisual.GrafoPanel panel;
    protected int id;
    public S1(GrafoVisual.GrafoPanel panel, int id) { this.panel = panel; this.id = id; }
    public void run() {
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        panel.markCompleted(id);
    }
}

class S2 extends S1 {
    public S2(GrafoVisual.GrafoPanel panel, int id) { super(panel, id); }
}

class S3 implements Runnable {
    protected GrafoVisual.GrafoPanel panel;
    protected Thread s1;
    protected int id;
    public S3(GrafoVisual.GrafoPanel panel, Thread s1, int id) { this.panel = panel; this.s1 = s1; this.id = id; }
    public void run() {
        try { s1.join(); Thread.sleep(1000); } catch (InterruptedException e) {}
        panel.markCompleted(id);
    }
}

class S4 extends S3 {
    private Thread s2;
    public S4(GrafoVisual.GrafoPanel panel, Thread s3, Thread s2, int id) {
        super(panel, s3, id);
        this.s2 = s2;
    }
    public void run() {
        try { super.s1.join(); s2.join(); Thread.sleep(1000); } catch (InterruptedException e) {}
        panel.markCompleted(super.id);
    }
}

class S5 extends S3 {
    public S5(GrafoVisual.GrafoPanel panel, Thread s3, int id) {
        super(panel, s3, id);
    }
}

class S6 extends S3 {
    private Thread s5;
    public S6(GrafoVisual.GrafoPanel panel, Thread s4, Thread s5, int id) {
        super(panel, s4, id);
        this.s5 = s5;
    }
    public void run() {
        try { super.s1.join(); s5.join(); Thread.sleep(1000); } catch (InterruptedException e) {}
        panel.markCompleted(super.id);
    }
}

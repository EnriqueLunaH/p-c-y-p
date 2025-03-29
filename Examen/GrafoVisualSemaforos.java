package Examen;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Semaphore;

/*
 * S1 → S3
 * S2 → S4
 * S3 → S4
 * S3 → S5
 * S4 → S6
 * S5 → S6
*/

public class GrafoVisualSemaforos extends JFrame {
  private final GrafoPanel panel;
  
  // Definición de semáforos para sincronizar los procesos
  private static final Semaphore s1 = new Semaphore(0);
  private static final Semaphore s2 = new Semaphore(0);
  private static final Semaphore s3 = new Semaphore(0);
  private static final Semaphore s4 = new Semaphore(0);
  private static final Semaphore s5 = new Semaphore(0);
  private static final Semaphore s6 = new Semaphore(0);

  public GrafoVisualSemaforos() {
    // Configuración de la ventana principal
    setTitle("Grafo de Procesos");
    setSize(600, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    panel = new GrafoPanel();
    add(panel);
  }

  public class GrafoPanel extends JPanel {
    private boolean[] completed = new boolean[6]; // Array para marcar los procesos completados

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
      // Dibujar un círculo con texto en el centro
      g.setColor(Color.LIGHT_GRAY);
      g.fillOval(x - 30, y - 30, 60, 60);
      g.setColor(Color.BLACK);
      g.drawOval(x - 30, y - 30, 60, 60);
      g.drawString(text, x - 10, y + 5);
    }

    private void drawLine(Graphics g, int x1, int y1, int x2, int y2) {
      // Dibujar una línea entre dos puntos
      g.setColor(Color.BLACK);
      g.drawLine(x1, y1, x2, y2);
    }

    public synchronized void markCompleted(int index) {
      // Marcar un proceso como completado y repintar el panel
      completed[index] = true;
      repaint();
    }
  }

  private void startProcesses() {
    // Iniciar el proceso S1
    new Thread(() -> {
      try {
        Thread.sleep(1000); // Simular trabajo
        panel.markCompleted(0); // Marcar S1 como completado
        s1.release(); // Liberar semáforo S1
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();

    // Iniciar el proceso S2
    new Thread(() -> {
      try {
        Thread.sleep(1000); // Simular trabajo
        panel.markCompleted(1); // Marcar S2 como completado
        s2.release(); // Liberar semáforo S2
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();

    // Iniciar el proceso S3
    new Thread(() -> {
      try {
        s1.acquire(); // Esperar a que S1 se complete
        Thread.sleep(1000); // Simular trabajo
        panel.markCompleted(2); // Marcar S3 como completado
        s3.release(2); // Liberar semáforo S3
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();

    // Iniciar el proceso S4
    new Thread(() -> {
      try {
        s2.acquire(); // Esperar a que S2 se complete
        s3.acquire(); // Esperar a que S3 se complete
        Thread.sleep(1000); // Simular trabajo
        panel.markCompleted(3); // Marcar S4 como completado
        s4.release(); // Liberar semáforo S4
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();

    // Iniciar el proceso S5
    new Thread(() -> {
      try {
        s3.acquire(); // Esperar a que S3 se complete
        Thread.sleep(1000); // Simular trabajo
        panel.markCompleted(4); // Marcar S5 como completado
        s5.release(); // Liberar semáforo S5
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();

    // Iniciar el proceso S6
    new Thread(() -> {
      try {
        s4.acquire(); // Esperar a que S4 se complete
        s5.acquire(); // Esperar a que S5 se complete
        Thread.sleep(1000); // Simular trabajo
        panel.markCompleted(5); // Marcar S6 como completado
        s6.release(); // Liberar semáforo S6
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
  }

  public static void main(String[] args) {
    // Iniciar la aplicación Swing
    SwingUtilities.invokeLater(() -> {
      GrafoVisualSemaforos frame = new GrafoVisualSemaforos();
      frame.setVisible(true);
      frame.startProcesses();
    });
  }
}

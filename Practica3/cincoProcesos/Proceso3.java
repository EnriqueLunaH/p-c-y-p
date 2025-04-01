package Practica3.cincoProcesos;

public class Proceso3 extends Procesos2 implements Runnable {
  public void run() {
    B.WAIT(); // Espera señal de P1

    mutex.WAIT(); // Bloquea consola
    System.out.println("Soy el proceso #3 y la instruccion S3");
    try {
      Thread.sleep(200); // Pausa para legibilidad
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    mutex.SIGNAL(); // Libera consola

    D.SIGNAL(); // Señal para P4
  }
}
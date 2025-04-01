package Practica3.cincoProcesos;

public class Proceso2 extends Procesos2 implements Runnable {
  public void run() {
    A.WAIT(); // Espera señal de P1

    mutex.WAIT(); // Bloquea consola
    System.out.println("Soy el proceso #2 y la instruccion S2");
    try {
      Thread.sleep(1000); // Pausa para salida visible
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    mutex.SIGNAL(); // Libera consola

    C.SIGNAL(); // Señal para P4
  }
}
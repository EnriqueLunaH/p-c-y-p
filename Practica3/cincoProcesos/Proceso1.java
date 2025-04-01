package Practica3.cincoProcesos;

public class Proceso1 extends Procesos2 implements Runnable {
  public void run() {
    mutex.WAIT(); // Bloquea la consola
    System.out.println("Soy el proceso #1 y la instruccion S1");
    try {
      Thread.sleep(1000); // Pequeña pausa para visualizar mejor la salida
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    mutex.SIGNAL(); // Libera la consola

    A.SIGNAL(); // Desbloquea P2
    B.SIGNAL(); // Desbloquea P3
  }
}
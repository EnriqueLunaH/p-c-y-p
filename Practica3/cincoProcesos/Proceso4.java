package Practica3.cincoProcesos;

public class Proceso4 extends Procesos2 implements Runnable {
  public void run() {
    C.WAIT(); // Espera señal de P2
    D.WAIT(); // Espera señal de P3

    mutex.WAIT(); // Bloquea consola
    System.out.println("Soy el proceso #4 y la instruccion S4");
    try {
      Thread.sleep(1000); // Pausa tras S4
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    mutex.SIGNAL(); // Libera consola

    mutex.WAIT(); // Bloquea consola nuevamente
    System.out.println("Soy el proceso #4 y la instruccion S5");
    try {
      Thread.sleep(1000); // Pausa tras S5
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    mutex.SIGNAL(); // Libera consola
  }
}
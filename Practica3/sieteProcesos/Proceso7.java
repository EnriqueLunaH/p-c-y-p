package Practica3.sieteProcesos;

public class Proceso7 extends ProcesosBase implements Runnable {
  public void run() {
    g.WAIT(); // Espera primera señal (desde Proceso5)
    g.WAIT(); // Espera segunda señal (desde Proceso6)

    mutex.WAIT();
    System.out.println("Soy el proceso #7 y la instrucción S7");
    
    // simulación de trabajo
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt(); // Manejo de interrupción
    }

    mutex.SIGNAL();
  }
}
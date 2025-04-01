package Practica3.sieteProcesos;

public class Proceso6 extends ProcesosBase implements Runnable {
  public void run() {
    d.WAIT(); // Espera señal desde Proceso4
    e.WAIT(); // Espera señal desde Proceso3

    mutex.WAIT();
    System.out.println("Soy el proceso #6 y la instrucción S6");

    // simulación de trabajo
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt(); // Manejo de interrupción
    }

    mutex.SIGNAL();

    g.SIGNAL(); // Señal para Proceso7
  }
}
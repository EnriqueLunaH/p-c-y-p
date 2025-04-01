package Practica3.sieteProcesos;

public class Proceso5 extends ProcesosBase implements Runnable {
  public void run() {
    f.WAIT(); // Espera señal desde Proceso4

    mutex.WAIT();
    System.out.println("Soy el proceso #5 y la instrucción S5");

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
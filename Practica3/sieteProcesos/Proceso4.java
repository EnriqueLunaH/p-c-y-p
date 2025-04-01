package Practica3.sieteProcesos;

public class Proceso4 extends ProcesosBase implements Runnable {
  public void run() {
    c.WAIT(); // Espera señal desde Proceso2

    mutex.WAIT();
    System.out.println("Soy el proceso #4 y la instrucción S4");

    // simulación de trabajo
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt(); // Manejo de interrupción
    }

    mutex.SIGNAL();

    d.SIGNAL(); // Habilita Proceso6
    f.SIGNAL(); // Habilita Proceso5
  }
}
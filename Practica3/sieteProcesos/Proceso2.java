package Practica3.sieteProcesos;

public class Proceso2 extends ProcesosBase implements Runnable {
  public void run() {
    a.WAIT(); // Espera señal desde Proceso1

    mutex.WAIT();
    System.out.println("Soy el proceso #2 y la instrucción S2");

    // simulación de trabajo
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt(); // Manejo de interrupción
    }

    mutex.SIGNAL();

    c.SIGNAL(); // Habilita Proceso4 (S4)
  }
}
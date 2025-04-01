package Practica3.sieteProcesos;

public class Proceso3 extends ProcesosBase implements Runnable {
  public void run() {
    b.WAIT(); // Espera señal desde Proceso1

    mutex.WAIT();
    System.out.println("Soy el proceso #3 y la instrucción S3");

    // simulación de trabajo
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt(); // Manejo de interrupción
    }

    mutex.SIGNAL();

    e.SIGNAL(); // Habilita Proceso6 (S6)
  }
}
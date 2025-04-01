package Practica3.sieteProcesos;

public class Proceso1 extends ProcesosBase implements Runnable {
  public void run() {
    mutex.WAIT(); // Entra en sección crítica (impresión)
    System.out.println("Soy el proceso #1 y la instrucción S1");
    
    // simulación de trabajo
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt(); // Manejo de interrupción
    }

    mutex.SIGNAL(); // Sale de sección crítica

    a.SIGNAL(); // Habilita Proceso2 (S2)
    b.SIGNAL(); // Habilita Proceso3 (S3)
  }
}
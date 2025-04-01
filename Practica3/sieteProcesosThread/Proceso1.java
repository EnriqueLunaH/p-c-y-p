package Practica3.sieteProcesosThread;

// Proceso que ejecuta S1 y habilita a P2 y P3
public class Proceso1 extends Thread {
  public void run() {
    ProcesosBase.mutex.WAIT(); // Entra en sección crítica
    System.out.println("Soy el proceso #1 y la instrucción S1");
    try { Thread.sleep(200); } catch (InterruptedException e) {}
    ProcesosBase.mutex.SIGNAL(); // Sale de sección crítica

    ProcesosBase.a.SIGNAL(); // Habilita S2 (P2)
    ProcesosBase.b.SIGNAL(); // Habilita S3 (P3)
  }
}

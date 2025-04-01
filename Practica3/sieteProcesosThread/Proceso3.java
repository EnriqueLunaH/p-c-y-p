package Practica3.sieteProcesosThread;

// Proceso que ejecuta S3, tras recibir señal 'b'
public class Proceso3 extends Thread {
  public void run() {
    ProcesosBase.b.WAIT(); // Espera a que S1 termine

    ProcesosBase.mutex.WAIT();
    System.out.println("Soy el proceso #3 y la instrucción S3");
    try { Thread.sleep(200); } catch (InterruptedException e) {}
    ProcesosBase.mutex.SIGNAL();

    ProcesosBase.e.SIGNAL(); // Habilita parte de S6 (P6)
  }
}
package Practica3.sieteProcesosThread;

// Proceso que ejecuta S6, tras recibir señales 'd' y 'e'
public class Proceso6 extends Thread {
  public void run() {
    ProcesosBase.d.WAIT(); // Desde S4
    ProcesosBase.e.WAIT(); // Desde S3

    ProcesosBase.mutex.WAIT();
    System.out.println("Soy el proceso #6 y la instrucción S6");
    try { Thread.sleep(200); } catch (InterruptedException e) {}
    ProcesosBase.mutex.SIGNAL();

    ProcesosBase.g.SIGNAL(); // Envía señal a S7 (P7)
  }
}

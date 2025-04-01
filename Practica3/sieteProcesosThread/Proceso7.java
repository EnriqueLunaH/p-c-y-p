package Practica3.sieteProcesosThread;

// Proceso que ejecuta S7, espera dos señales 'g' (de P5 y P6)
public class Proceso7 extends Thread {
  public void run() {
    ProcesosBase.g.WAIT(); // Desde P5
    ProcesosBase.g.WAIT(); // Desde P6

    ProcesosBase.mutex.WAIT();
    System.out.println("Soy el proceso #7 y la instrucción S7");
    try { Thread.sleep(200); } catch (InterruptedException e) {}
    ProcesosBase.mutex.SIGNAL();
  }
}
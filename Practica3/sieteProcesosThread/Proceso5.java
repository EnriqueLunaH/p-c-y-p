package Practica3.sieteProcesosThread;

// Proceso que ejecuta S5, tras recibir señal 'f'
public class Proceso5 extends Thread {
  public void run() {
    ProcesosBase.f.WAIT(); // Espera a que S4 termine

    ProcesosBase.mutex.WAIT();
    System.out.println("Soy el proceso #5 y la instrucción S5");
    try { Thread.sleep(200); } catch (InterruptedException e) {}
    ProcesosBase.mutex.SIGNAL();

    ProcesosBase.g.SIGNAL(); // Envía señal a S7 (P7)
  }
}

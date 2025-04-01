package Practica3.sieteProcesosThread;

// Proceso que ejecuta S2, tras recibir señal 'a'
public class Proceso2 extends Thread {
  public void run() {
    ProcesosBase.a.WAIT(); // Espera a que S1 termine

    ProcesosBase.mutex.WAIT();
    System.out.println("Soy el proceso #2 y la instrucción S2");
    try { Thread.sleep(200); } catch (InterruptedException e) {}
    ProcesosBase.mutex.SIGNAL();

    ProcesosBase.c.SIGNAL(); // Habilita S4 (P4)
  }
}


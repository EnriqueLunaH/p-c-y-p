package Practica3.sieteProcesosThread;

// Proceso que ejecuta S4, tras recibir señal 'c'
public class Proceso4 extends Thread {
  public void run() {
    ProcesosBase.c.WAIT(); // Espera a que S2 termine

    ProcesosBase.mutex.WAIT();
    System.out.println("Soy el proceso #4 y la instrucción S4");
    try { Thread.sleep(200); } catch (InterruptedException e) {}
    ProcesosBase.mutex.SIGNAL();

    ProcesosBase.d.SIGNAL(); // Habilita parte de S6 (P6)
    ProcesosBase.f.SIGNAL(); // Habilita S5 (P5)
  }
}
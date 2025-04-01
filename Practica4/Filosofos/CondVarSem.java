package Practica4.Filosofos;

// Clase alternativa que modela una condición con semáforo lógico simple
public class CondVarSem {
  private boolean signal = false; // Estado de la señal

  public synchronized void await() {
    while (!signal) {
      try {
        wait(); // Espera hasta recibir señal
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    signal = false; // Resetea la señal
  }

  public synchronized void signal() {
    signal = true;
    notify(); // Despierta a un hilo
  }

  public synchronized void signalAll() {
    signal = true;
    notifyAll(); // Despierta a todos
  }
}

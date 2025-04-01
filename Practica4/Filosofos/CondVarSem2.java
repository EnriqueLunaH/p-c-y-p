package Practica4.Filosofos;

import java.util.LinkedList;
import java.util.Queue;

// Variante con cola de espera explícita
public class CondVarSem2 {
  private final Queue<Thread> waitingQueue = new LinkedList<>();

  public synchronized void await() {
    Thread current = Thread.currentThread();
    waitingQueue.add(current);
    try {
      wait(); // El hilo se bloquea
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    } finally {
      waitingQueue.remove(current); // Se remueve al desbloquearse
    }
  }

  public synchronized void signal() {
    notify(); // Despierta a un hilo
  }

  public synchronized void signalAll() {
    notifyAll(); // Despierta a todos los hilos
  }
}
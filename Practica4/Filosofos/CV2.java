package Practica4.Filosofos;

// Clase que modela una variable de condición básica con nombre
public class CV2 {
  private final String nombre; // Nombre de la condición (solo decorativo)

  public CV2(String nombre) {
    this.nombre = nombre;
  }

  // Método para hacer que el hilo espere
  public void DELAY(Object monitor) {
    try {
      monitor.wait(); // El hilo se bloquea y libera el monitor
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  // Método para notificar a los hilos esperando
  public void RESUME(Object monitor) {
    monitor.notifyAll(); // Despierta a todos los hilos esperando en ese monitor
  }
}

package Practica3.sieteProcesosThread;

// Clase que implementa un semáforo binario
public class SemaforoBinario {
  private int contador;

  // Constructor que inicializa el semáforo con 0 o 1
  public SemaforoBinario(int valorInicial) {
    this.contador = valorInicial;
  }

  // Método WAIT: bloquea el hilo si el semáforo está en 0
  public synchronized void WAIT() {
    while (contador == 0) {
      try {
        wait(); // Libera el monitor y entra en espera
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    contador--; // Reduce el valor del semáforo a 0
  }

  // Método SIGNAL: libera el semáforo y notifica a un hilo
  public synchronized void SIGNAL() {
    contador = 1; // Restaura el valor a 1 (activo)
    notify();     // Despierta a un hilo en espera
  }
}

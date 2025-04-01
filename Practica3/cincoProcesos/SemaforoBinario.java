package Practica3.cincoProcesos;

// Clase que modela un semáforo binario simple
public class SemaforoBinario {
  private int contador;

  // Constructor: inicializa el semáforo con 0 o 1
  public SemaforoBinario(int valorInicial) {
    this.contador = valorInicial;
  }

  // Operación WAIT: espera si el semáforo está en 0
  public synchronized void WAIT() {
    while (contador == 0) {
      try {
        wait(); // El hilo se bloquea hasta que alguien llame a SIGNAL()
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    contador--; // Reduce el contador a 0 (bloqueado)
  }

  // Operación SIGNAL: libera el semáforo
  public synchronized void SIGNAL() {
    contador = 1; // Establece a 1 (liberado)
    notify();     // Despierta a un hilo en espera
  }
}
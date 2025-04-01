package Practica3.sieteProcesos;

// Clase que simula un semáforo binario para sincronización de hilos
public class SemaforoBinario {
  private int contador;

  // Constructor: inicializa el valor del semáforo (0 o 1)
  public SemaforoBinario(int valorInicial) {
    this.contador = valorInicial;
  }

  // WAIT: bloquea al hilo si el semáforo está en 0
  public synchronized void WAIT() {
    while (contador == 0) {
      try {
        wait(); // El hilo espera hasta recibir señal
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    contador--; // Al salir del ciclo, decrementa el contador
  }

  // SIGNAL: libera el semáforo y notifica a un hilo en espera
  public synchronized void SIGNAL() {
    contador = 1; // Activa el semáforo
    notify();     // Despierta a un hilo en espera
  }
}
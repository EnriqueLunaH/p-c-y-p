package Practica4; // Paquete de la clase

// Clase que representa el hilo del Hermano 1
public class Hermano1 extends Thread {
  private final Monitor_Parcela parcela; // Referencia al monitor compartido

  // Constructor que recibe el monitor
  public Hermano1(Monitor_Parcela parcela) {
    this.parcela = parcela;
  }

  // Método que se ejecuta cuando el hilo comienza
  public void run() {
    while (parcela.debeContinuar()) {
      parcela.SiembraHermano_1(); // Llama al método de siembra del Hermano 1
    }
  }
}
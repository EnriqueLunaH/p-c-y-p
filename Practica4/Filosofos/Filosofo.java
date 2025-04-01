package Practica4.Filosofos;

// Clase que representa a un filósofo (implementa Runnable para usar en un hilo)
public class Filosofo implements Runnable {
  private final Mesa mesa; // Referencia al monitor compartido
  private final int id;    // Identificador del filósofo

  public Filosofo(Mesa mesa, int id) {
    this.mesa = mesa;
    this.id = id;
  }

  public void run() {
    for (int i = 0; i < 5; i++) { // Cada filósofo realiza 5 ciclos
      System.out.println("Filosofo " + id + " pensando");
      try {
        Thread.sleep(1000); // Simula que el filósofo piensa
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }

      mesa.coger(id); // Intenta tomar los tenedores
      System.out.println("Filosofo " + id + " comiendo");
      try {
        Thread.sleep(1000); // Simula que el filósofo come
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }

      mesa.poner(id); // Libera los tenedores
    }
  }
}
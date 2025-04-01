package Practica4.Filosofos;

// Monitor que gestiona los tenedores y controla el acceso de los filósofos
public class Mesa {
  private boolean[] tenedores = {true, true, true, true, true}; // true = libre
  private CV2[] okParaComer = new CV2[5]; // Condiciones para cada filósofo

  public Mesa() {
    for (int i = 0; i < 5; i++) {
      okParaComer[i] = new CV2("okParaComer" + i); // Se inicializan las condiciones
    }
  }

  // Método sincronizado para intentar tomar los tenedores
  public synchronized void coger(int i) {
    // Esperar mientras alguno de los tenedores no esté disponible
    while (!tenedores[(i + 1) % 5] || !tenedores[(i + 4) % 5]) {
      System.out.println("Filosofo " + i + " esperando");
      okParaComer[i].DELAY(this); // El filósofo espera
    }

    // Tenedores disponibles: se marcan como ocupados
    tenedores[(i + 1) % 5] = false;
    tenedores[(i + 4) % 5] = false;
  }

  // Método sincronizado para liberar los tenedores
  public synchronized void poner(int i) {
    System.out.println("Filosofo " + i + " termina");

    // Se liberan los tenedores
    tenedores[(i + 1) % 5] = true;
    tenedores[(i + 4) % 5] = true;

    // Se notifica a los vecinos si ahora pueden comer
    if (tenedores[(i + 2) % 5] && tenedores[i]) {
      okParaComer[(i + 1) % 5].RESUME(this);
    }
    if (tenedores[(i + 3) % 5] && tenedores[i]) {
      okParaComer[(i + 4) % 5].RESUME(this);
    }
  }
}
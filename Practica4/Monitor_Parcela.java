package Practica4; // Paquete del monitor

// Clase que actúa como monitor para coordinar el acceso a la parcela
public class Monitor_Parcela {
  private int arbolesSembrados = 0; // Contador de árboles sembrados
  private final int totalArboles;   // Total de árboles a sembrar (límite)
  private boolean turnoHermano1 = true; // Variable para alternar turnos

  // Constructor que recibe la cantidad total de árboles a sembrar
  public Monitor_Parcela(int totalArboles) {
    this.totalArboles = totalArboles;
  }

  // Método sincronizado para consultar si aún deben sembrarse árboles
  public synchronized boolean debeContinuar() {
    return arbolesSembrados < totalArboles;
  }

  // Método sincronizado que controla la siembra del Hermano 1
  public synchronized void SiembraHermano_1() {
    while (!turnoHermano1 && debeContinuar()) {
      try {
        wait(); // Espera si no es su turno
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
      }
    }

    if (!debeContinuar()) return; // Sale si ya no hay más árboles por sembrar

    arbolesSembrados++; // Incrementa el contador de árboles sembrados
    System.out.println(Thread.currentThread().getName() + ": Hermano 1 siembra el árbol: " + arbolesSembrados);
    try {
      Thread.sleep(500); // Simula el tiempo de siembra
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    turnoHermano1 = false; // Cambia el turno al Hermano 2
    notifyAll(); // Notifica a los hilos en espera
  }

  // Método sincronizado que controla la siembra del Hermano 2
  public synchronized void SiembraHermano_2() {
    while (turnoHermano1 && debeContinuar()) {
      try {
        wait(); // Espera si no es su turno
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
      }
    }

    if (!debeContinuar()) return; // Sale si ya no hay más árboles por sembrar

    arbolesSembrados++; // Incrementa el contador de árboles sembrados
    System.out.println(Thread.currentThread().getName() + ": Hermano 2 siembra el árbol: " + arbolesSembrados);
    try {
      Thread.sleep(500); // Simula el tiempo de siembra
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    turnoHermano1 = true; // Cambia el turno al Hermano 1
    notifyAll(); // Notifica a los hilos en espera
  }
}
package Practica3;

class TurnoManager {
  // Variable que almacena el número de turno actual
  private int turnoActual = 1;

  // Método sincronizado que bloquea al paciente hasta que sea su turno
  public synchronized void esperarTurno(int miTurno) throws InterruptedException {
    // Mientras no sea el turno del paciente, espera (wait libera el monitor y bloquea el hilo)
    while (miTurno != turnoActual) {
      wait();
    }
  }

  // Método sincronizado que avanza al siguiente turno y notifica a todos los hilos esperando
  public synchronized void siguienteTurno() {
    turnoActual++; // Se incrementa el turno actual
    notifyAll(); // Se despiertan todos los hilos para que verifiquen si ahora es su turno
  }
}

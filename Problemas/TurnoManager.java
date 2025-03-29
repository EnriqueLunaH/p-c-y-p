package Problemas;

public class TurnoManager {
  private int turnoActual = 1;

  public synchronized void esperarTurno(int miTurno) throws InterruptedException {
      while (miTurno != turnoActual) {
          wait();
      }
  }

  public synchronized void siguienteTurno() {
      turnoActual++;
      notifyAll();
  }
}
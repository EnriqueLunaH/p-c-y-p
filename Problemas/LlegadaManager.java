package Problemas;

public class LlegadaManager {
  private int turnoLlegada = 1;

  public synchronized void esperarTurnoLlegada(int miTurno) throws InterruptedException {
      while (miTurno != turnoLlegada) {
          wait();
      }
  }

  public synchronized void siguienteTurnoLlegada() {
      turnoLlegada++;
      notifyAll();
  }
}
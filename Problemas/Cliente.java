package Problemas;

public class Cliente implements Runnable {
  private final String nombre;
  private final Cajero cajero;
  private final TurnoManager turnoManager;
  private final LlegadaManager llegadaManager;
  private final int ordenLlegada;

  public Cliente(String nombre, Cajero cajero, TurnoManager turnoManager, LlegadaManager llegadaManager, int ordenLlegada) {
      this.nombre = nombre;
      this.cajero = cajero;
      this.turnoManager = turnoManager;
      this.llegadaManager = llegadaManager;
      this.ordenLlegada = ordenLlegada;
  }

  @Override
  public void run() {
      try {
          llegadaManager.esperarTurnoLlegada(ordenLlegada);
          System.out.println(nombre + " llega al cajero");
          llegadaManager.siguienteTurnoLlegada();

          turnoManager.esperarTurno(ordenLlegada);
          cajero.usarCajero(nombre);
          turnoManager.siguienteTurno();
      } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
      }
  }
}
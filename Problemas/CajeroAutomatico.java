package Problemas;

public class CajeroAutomatico {
  public static void main(String[] args) {
      int numeroClientes = 4;

      Cajero cajero = new Cajero();
      LlegadaManager llegadaManager = new LlegadaManager();
      TurnoManager turnoManager = new TurnoManager();

      for (int i = 1; i <= numeroClientes; i++) {
          Thread cliente = new Thread(new Cliente("Cliente " + i, cajero, turnoManager, llegadaManager, i));
          cliente.start();
      }
  }
}
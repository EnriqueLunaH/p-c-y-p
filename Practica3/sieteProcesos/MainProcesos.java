package Practica3.sieteProcesos;

// Clase principal que lanza todos los hilos
public class MainProcesos {
  public static void main(String[] args) {
    new Thread(new Proceso1()).start();
    new Thread(new Proceso2()).start();
    new Thread(new Proceso3()).start();
    new Thread(new Proceso4()).start();
    new Thread(new Proceso5()).start();
    new Thread(new Proceso6()).start();
    new Thread(new Proceso7()).start();
  }
}

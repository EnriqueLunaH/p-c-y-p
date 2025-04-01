package Practica3.cincoProcesos;

public class MainProcesos {
  public static void main(String[] args) {
    // Se crean los hilos con sus respectivas tareas
    Thread P1 = new Thread(new Proceso1());
    Thread P2 = new Thread(new Proceso2());
    Thread P3 = new Thread(new Proceso3());
    Thread P4 = new Thread(new Proceso4());

    // Inician todos los procesos concurrentemente
    P1.start();
    P2.start();
    P3.start();
    P4.start();
  }
}
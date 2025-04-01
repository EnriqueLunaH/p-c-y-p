package Practica3.sieteProcesosThread;

// Clase principal que lanza todos los procesos
public class MainSemaforos {
  public static void main(String[] args) {
    new Proceso1().start();
    new Proceso2().start();
    new Proceso3().start();
    new Proceso4().start();
    new Proceso5().start();
    new Proceso6().start();
    new Proceso7().start();
  }
}

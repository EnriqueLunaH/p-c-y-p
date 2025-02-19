package Practica1;

// Definir la clase ThreadUsingExtends que extiende de Thread
class ThreadUsingExtends extends Thread {
  // Atributos
  String name;

  // Constructor de la clase ThreadUsingExtends
  ThreadUsingExtends(String name) {
    this.name = name;
  }

  // Método run que se ejecuta al llamar al método start
  @Override
  public void run() {
    System.out.println("Hilo " + name + " - Estado: " + getState());
    try {
      Thread.sleep(2000); // Simula trabajo
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("Hilo " + name + " - Estado: " + getState());
  }

  // Método para imprimir la información del hilo
  public void printInfo() {
    System.out.println("Thread name: " + this.getName());
    System.out.println("ID: " + this.getId());
    System.out.println("Priority: " + this.getPriority());
    System.out.println("Alive: " + this.isAlive());
    System.out.println("State: " + this.getState());
  }
}

public class Program1thread {
    // Método para imprimir la información de un hilo
    public static void printThreadInfo(ThreadUsingExtends thread) {
      thread.printInfo();
    }

    public static void main(String[] args) {

      // Crear dos instancias de la clase ThreadUsingExtends
      Thread t1 = new ThreadUsingExtends("T1");
      Thread t2 = new ThreadUsingExtends("T2");
      Thread t3 = new ThreadUsingExtends("T3");
      Thread t4 = new ThreadUsingExtends("T4");

      // Imprimir estados antes de iniciar los hilos
      System.out.println("Estado de t1 antes de start(): " + t1.getState());
      System.out.println("Estado de t2 antes de start(): " + t2.getState());
      System.out.println("Estado de t3 antes de start(): " + t3.getState());
      System.out.println("Estado de t4 antes de start(): " + t4.getState());

      // Iniciar los hilos
      t1.start();
      t2.start();
      t3.start();
      t4.start();

      // Imprimir estados después de iniciar los hilos
      System.out.println("Estado de t1 después de start(): " + t1.getState());
      System.out.println("Estado de t2 después de start(): " + t2.getState());
      System.out.println("Estado de t3 después de start(): " + t3.getState());
      System.out.println("Estado de t4 después de start(): " + t4.getState());

      // esperar a que los hilos terminen
      try {
          t1.join();
          t2.join();
          t3.join();
          t4.join();
      } catch (InterruptedException e) {
          System.out.println(e);
      }

      // Imprimir estados después de que los hilos terminen
      System.out.println("Estado de t1 después de join(): " + t1.getState());
      System.out.println("Estado de t2 después de join(): " + t2.getState());
      System.out.println("Estado de t3 después de join(): " + t3.getState());
      System.out.println("Estado de t4 después de join(): " + t4.getState());

      // Imprimir informacion de los hilos
      printThreadInfo((ThreadUsingExtends) t1);
      printThreadInfo((ThreadUsingExtends) t2);
      printThreadInfo((ThreadUsingExtends) t3);
      printThreadInfo((ThreadUsingExtends) t4);
    }
}
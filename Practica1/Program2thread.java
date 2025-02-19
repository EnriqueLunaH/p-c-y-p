package Practica1;

class TaskThread extends Thread {
  // Constructor que asigna un nombre al hilo
  public TaskThread(String name) {
    super(name);
  }

  @Override
  public void run() {
    // Imprimir el nombre del hilo en ejecución
    System.out.println(getName() + " en ejecución");
    try {
      // Simular trabajo durmiendo el hilo por 2000 milisegundos
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      // Imprimir un mensaje si el hilo es interrumpido
      System.out.println(getName() + " interrumpido");
    }
    // Imprimir un mensaje indicando que el hilo ha finalizado
    System.out.println(getName() + " finalizado");
  }
}

class MonitorThread extends Thread {
  private final Thread threadToMonitor;

  // Constructor que asigna el hilo a monitorear y un nombre al monitor
  public MonitorThread(Thread threadToMonitor, String name) {
    super(name);
    this.threadToMonitor = threadToMonitor;
  }

  @Override
  public void run() {
    // Obtener el estado inicial del hilo
    Thread.State previousState = threadToMonitor.getState();
    // Imprimir el estado inicial del hilo
    System.out.println(threadToMonitor.getName() + " estado inicial: " + previousState);
    // Mientras el hilo no haya terminado
    while (threadToMonitor.getState() != Thread.State.TERMINATED) {
      // Obtener el estado actual del hilo
      Thread.State currentState = threadToMonitor.getState();
      // Si el estado actual es diferente al estado anterior
      if (currentState != previousState) {
        // Imprimir el cambio de estado
        System.out.println(threadToMonitor.getName() + " cambió a estado: " + currentState);
        // Actualizar el estado anterior
        previousState = currentState;
      }
    }
    // Imprimir el estado final del hilo
    System.out.println(threadToMonitor.getName() + " estado final: " + threadToMonitor.getState());
  }
}

public class Program2thread {
  public static void main(String[] args) {
    // Crear el hilo con la tarea definida y asignarle un nombre
    TaskThread thread = new TaskThread("Hilo-1");

    // Crear un monitor para observar el estado del hilo
    MonitorThread monitor = new MonitorThread(thread, "Monitor-Hilo");

    // Iniciar el monitor y el hilo
    monitor.start();
    thread.start();

    // Esperar la finalización del hilo con join
    try {
      thread.join();
      monitor.join();
    } catch (InterruptedException e) {
      // Imprimir un mensaje si hay un error al esperar los hilos
      System.out.println("Error al esperar los hilos");
    }
  }
}

package Practica1;

public class Program2runnable {
  public static void main(String[] args) {
    // Crear un hilo usando la interfaz Runnable
    Runnable task = () -> {
      // Imprimir el nombre del hilo en ejecución
      System.out.println(Thread.currentThread().getName() + " en ejecución");
      try {
        // Simular trabajo durmiendo el hilo por 2000 milisegundos
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        // Imprimir un mensaje si el hilo es interrumpido
        System.out.println(Thread.currentThread().getName() + " interrumpido");
      }
      // Imprimir un mensaje indicando que el hilo ha finalizado
      System.out.println(Thread.currentThread().getName() + " finalizado");
    };

    // Crear el hilo con la tarea definida y asignarle un nombre
    Thread thread = new Thread(task, "Hilo-1");

    // Crear un monitor para observar el estado del hilo
    Thread monitor = new Thread(() -> {
      // Obtener el estado inicial del hilo
      Thread.State previousState = thread.getState();
      // Imprimir el estado inicial del hilo
      System.out.println(thread.getName() + " estado inicial: " + previousState);
      // Mientras el hilo no haya terminado
      while (thread.getState() != Thread.State.TERMINATED) {
        // Obtener el estado actual del hilo
        Thread.State currentState = thread.getState();
        // Si el estado actual es diferente al estado anterior
        if (currentState != previousState) {
          // Imprimir el cambio de estado
          System.out.println(thread.getName() + " cambió a estado: " + currentState);
          // Actualizar el estado anterior
          previousState = currentState;
        }
      }
      // Imprimir el estado final del hilo
      System.out.println(thread.getName() + " estado final: " + thread.getState());
    }, "Monitor-Hilo");

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

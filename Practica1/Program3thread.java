package Practica1;

// Definir la clase ThreadUsingExtends que extiende de Thread
class ThreadUsingExtends extends Thread {
  private String name;

  // Constructor que asigna un nombre al hilo
  public ThreadUsingExtends(String name) {
    this.name = name;
  }

  // Método para verificar si un número es par
  public String esPar(int i) {
    return (i % 2 == 0) ? "es par" : "es impar";
  }

  // Método para verificar si un número es primo
  public String esPrimo(int i) {
    if (i <= 1) return "no es primo";
    for (int j = 2; j < i; j++) {
      if (i % j == 0) return "no es primo";
    }
    return "es primo";
  }

  // Método run que se ejecuta al llamar al método start
  @Override
  public void run() {
    for (int i = 1; i <= 10; i += 1) {
      // Imprimir el nombre del hilo, el número, si es par o impar y si es primo o no
      System.out.println(name + " - " + i + " " + esPar(i) + " y " + esPrimo(i));
    }
  }
}

public class Program3thread {
  public static void main(String[] args) {
    // Crear tres instancias de la clase ThreadUsingExtends
    ThreadUsingExtends t1 = new ThreadUsingExtends("T1");
    ThreadUsingExtends t2 = new ThreadUsingExtends("T2");
    ThreadUsingExtends t3 = new ThreadUsingExtends("T3");

    // Iniciar los hilos
    t1.start();
    t2.start();
    t3.start();
  }
}
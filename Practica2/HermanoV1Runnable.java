package Practica2;

class Jardin {
  public synchronized void sembrar(String nombre) {
    for (int i = 1; i <= 10; i++) {
      System.out.println(nombre + " esta sembrando el arbol " + i);

      try {
          Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      System.out.println(nombre + " ha terminado de sembrar el arbol " + i);
    }
  }
}

class Hermano implements Runnable {
  private final Jardin jardin;
  private final String nombre;

  public Hermano(Jardin jardin, String nombre) {
    this.jardin = jardin;
    this.nombre = nombre;
  }

  @Override
  public void run() {
      jardin.sembrar(nombre);
  }
}

public class HermanoV1Runnable {
  public static void main(String[] args) {
    Jardin jardin = new Jardin();
    Hermano hermano1 = new Hermano(jardin, "Hermano 1");
    Hermano hermano2 = new Hermano(jardin, "Hermano 2");

    Thread t1 = new Thread(hermano1);
    Thread t2 = new Thread(hermano2);

    t1.start();
    try {
      t1.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    t2.start();
    try {
      t2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Ambos hermanos han terminado de sembrar los árboles");
  }
}

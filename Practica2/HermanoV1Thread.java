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

class Hermano extends Thread {
  private final Jardin jardin;

  public Hermano(Jardin jardin, String nombre) {
    super(nombre);
    this.jardin = jardin;
  }

  @Override
  public void run() {
    jardin.sembrar(getName());
  }
}

public class HermanoV1Thread {
  public static void main(String[] args) {
    Jardin jardin = new Jardin();
    Hermano hermano1 = new Hermano(jardin, "Hermano 1");
    Hermano hermano2 = new Hermano(jardin, "Hermano 2");

    hermano1.start();

    try {
      hermano1.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    hermano2.start();

    try {
      hermano2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Ambos hermanos han terminado de sembrar los árboles");
  }
}

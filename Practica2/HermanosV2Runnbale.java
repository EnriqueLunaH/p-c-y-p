package Practica2;

class Jardin {
  private boolean turnoHermano1 = true;

  public synchronized void sembrar(String nombre, boolean esHermano1) {
    for (int i = 1; i <= 10; i++) {
      synchronized (this) {
        while (turnoHermano1 != esHermano1) {
          try {
            wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }

        System.out.println(nombre + " esta sembrando el arbol " + i);

        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        System.out.println(nombre + " ha terminado de sembrar el arbol " + i);

        turnoHermano1 = !esHermano1;
        notifyAll();
      }
    }
  }
}

class Hermano implements Runnable {
  private final Jardin jardin;
  private final String nombre;
  private final boolean esHermano1;

  public Hermano(Jardin jardin, String nombre, boolean esHermano1) {
    this.jardin = jardin;
    this.nombre = nombre;
    this.esHermano1 = esHermano1;
  }

  @Override
  public void run() {
    jardin.sembrar(nombre, esHermano1);
  }
}

public class HermanosV2Runnbale {
  public static void main(String[] args) {
    Jardin jardin = new Jardin();
    Hermano hermano1 = new Hermano(jardin, "Hermano 1", true);
    Hermano hermano2 = new Hermano(jardin, "Hermano 2", false);

    Thread t1 = new Thread(hermano1);
    Thread t2 = new Thread(hermano2);

    t1.start();
    t2.start();

    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Ambos hermanos han terminado de sembrar los árboles");
  } 
}

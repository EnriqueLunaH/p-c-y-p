package Practica2;

class Contador {
  private int sumaPares = 0;
  private int sumaImpares = 0;

  public synchronized void sumarNumero(int num, boolean esPar) {
    if (esPar) {
      sumaPares += num;
    } else {
      sumaImpares += num;
    }
  }

  public void imprimirSumaTotal() {
    System.out.println("Suma total de pares: " + sumaPares);
    System.out.println("Suma total de impares: " + sumaImpares);
    System.out.println("Suma total de pares e impares: " + (sumaPares + sumaImpares));
  }
}

class HiloNumeros implements Runnable {
  private final Contador contador;
  private final boolean esPar;
  private final String nombreHilo;

  public HiloNumeros(Contador contador, boolean esPar, String nombreHilo) {
      this.contador = contador;
      this.esPar = esPar;
      this.nombreHilo = nombreHilo;
  }

  @Override
  public void run() {
      int inicio = esPar ? 2 : 1;
      for (int i = inicio; i <= 10; i += 2) {
          System.out.println(nombreHilo + " - " + (esPar ? "Par" : "Impar") + ": " + i);
          contador.sumarNumero(i, esPar);
          try {
            Thread.sleep(500); // Simula procesamiento
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
      }
  }
}

public class NumerosEstadosRunnable {
  public static void main(String[] args) {
    Contador contador = new Contador();

    // Crear dos hilos con la misma clase, pero con configuraciones diferentes
    Thread hiloA = new Thread(new HiloNumeros(contador, false, "Hilo A")); // Imprime impares
    Thread hiloB = new Thread(new HiloNumeros(contador, true, "Hilo B"));  // Imprime pares

    // Iniciar hilos
    hiloA.start();
    hiloB.start();

    // Esperar a que los hilos terminen
    try {
      hiloA.join();
      hiloB.join();
    } catch (InterruptedException e) {
      System.out.println("Error en la sincronización de los hilos.");
    }

    // Imprimir la suma total
    contador.imprimirSumaTotal();
  }
}

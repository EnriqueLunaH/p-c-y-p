package Practica4.Filosofos;

// Clase principal para lanzar el problema de los filósofos
public class MonFilos {
  public static void main(String args[]) {
    Mesa mesa = new Mesa(); // Se crea el monitor compartido

    // Se crean e inician los 5 hilos-filósofos
    for (int i = 0; i < 5; i++) {
      new Thread(new Filosofo(mesa, i)).start();
    }
  }
}
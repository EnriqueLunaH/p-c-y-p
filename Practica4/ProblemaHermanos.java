package Practica4; // Define el paquete donde se encuentra el programa

// Clase principal del programa
public class ProblemaHermanos {
  public static void main(String[] args) {
    // Cantidad total de árboles a sembrar
    int totalArboles = 10;

    // Se crea una instancia del monitor compartido, pasándole el total de árboles
    Monitor_Parcela parcela = new Monitor_Parcela(totalArboles);

    // Se crean dos hilos, uno para cada hermano
    Hermano1 h1 = new Hermano1(parcela);
    Hermano2 h2 = new Hermano2(parcela);

    // Se asignan nombres a los hilos para identificar su salida
    h1.setName("H1");
    h2.setName("H2");

    // Se inician ambos hilos
    h1.start();
    h2.start();
  }
}
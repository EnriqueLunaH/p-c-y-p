package Practica4.TresHermanos; // Declaramos el paquete del proyecto

// Clase principal donde se inicia la ejecución del programa
public class Problema3Hermanos {
    public static void main(String[] args) {
        int totalArboles = 15; // Definimos el número total de árboles que se desean sembrar (120)

        // Creamos una instancia del monitor compartido que regula la siembra
        Monitor_Parcela parcela = new Monitor_Parcela(totalArboles);

        // Creamos los tres hilos, uno por cada hermano
        Hermano1 h1 = new Hermano1(parcela);
        Hermano2 h2 = new Hermano2(parcela);
        Hermano3 h3 = new Hermano3(parcela);

        // Asignamos nombres para diferenciarlos en la salida
        h1.setName("H1");
        h2.setName("H2");
        h3.setName("H3");

        // Iniciamos los tres hilos
        h1.start();
        h2.start();
        h3.start();
    }
}

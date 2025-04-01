package Practica4.ClubGolf;

// Clase principal del programa
public class Simulador {
    public static void main(String[] args) {
        int numPelotas = 20;     // Cantidad total de pelotas disponibles
        int numPalos = 20;       // Cantidad total de palos disponibles
        int numJugadores = 14;   // Total de jugadores (7 novatos, 7 expertos)
        int repeticiones = 5;    // Número de veces que cada jugador repetirá su ciclo

        Club club = new Club(numPelotas, numPalos); // Se crea el monitor con el material

        // Crear e iniciar los hilos de los jugadores
        for (int i = 0; i < numJugadores; i++) {
            String tipo = (i < 7) ? "novato" : "experto"; // Los primeros 7 son novatos
            Thread jugador = new Thread(new Jugador(i + 1, tipo, repeticiones, club));
            jugador.start();
        }
    }
}
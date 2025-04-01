package Practica4.ClubGolf;

import java.util.Random;

// Clase que representa a un jugador del club (implementa Runnable)
public class Jugador implements Runnable {
    private final int id;             // Identificador del jugador
    private final String tipo;        // "novato" o "experto"
    private final int repeticiones;   // Número de veces que jugará
    private final Club club;          // Referencia al monitor

    private final int pelotasRequeridas; // Pelotas que este jugador usa
    private final int palosRequeridos;   // Palos que este jugador usa

    private final Random random = new Random(); // Para generar tiempos aleatorios

    // Constructor
    public Jugador(int id, String tipo, int repeticiones, Club club) {
        this.id = id;
        this.tipo = tipo;
        this.repeticiones = repeticiones;
        this.club = club;

        // Asignar requerimientos según el tipo de jugador
        if (tipo.equals("novato")) {
            this.pelotasRequeridas = random.nextInt(5) + 1; // Entre 1 y 5 pelotas
            this.palosRequeridos = 2;                       // Siempre 2 palos
        } else {
            this.pelotasRequeridas = 1;                     // Siempre 1 pelota
            this.palosRequeridos = random.nextInt(4) + 2;   // Entre 2 y 5 palos
        }
    }

    // Getters para el monitor
    public int getId() { return id; }

    public String getTipo() { return tipo; }

    public int getPelotasRequeridas() { return pelotasRequeridas; }

    public int getPalosRequeridos() { return palosRequeridos; }

    // Lógica principal del jugador
    public void run() {
        for (int i = 0; i < repeticiones; i++) {
            club.reservarMaterial(this); // Pide recursos al club

            System.out.println("Jugador " + id + " (" + tipo + ") está jugando...");
            dormir(); // Simula el tiempo de juego

            club.devolverMaterial(this); // Devuelve recursos al club

            System.out.println("Jugador " + id + " (" + tipo + ") está descansando...");
            dormir(); // Simula el tiempo de descanso
        }
    }

    // Tiempo aleatorio de espera (hasta 1 segundo)
    private void dormir() {
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

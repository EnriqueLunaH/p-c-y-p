package Practica4.ClubGolf;

// Clase que funciona como monitor para gestionar acceso a los recursos
public class Club {
    private int pelotasDisponibles;  // Pelotas que se pueden alquilar
    private int palosDisponibles;    // Palos que se pueden alquilar

    public Club(int pelotas, int palos) {
        this.pelotasDisponibles = pelotas;
        this.palosDisponibles = palos;
    }

    // Método sincronizado para reservar recursos
    public synchronized void reservarMaterial(Jugador jugador) {
        int pelotas = jugador.getPelotasRequeridas(); // Pelotas necesarias
        int palos = jugador.getPalosRequeridos();     // Palos necesarios

        // Espera si no hay suficientes recursos disponibles
        while (pelotas > pelotasDisponibles || palos > palosDisponibles) {
            try {
                wait(); // Libera el monitor y se bloquea hasta que haya recursos
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Asignar los recursos al jugador
        pelotasDisponibles -= pelotas;
        palosDisponibles -= palos;

        // Mostrar la acción
        System.out.println("Jugador " + jugador.getId() + " (" + jugador.getTipo() + ") alquila "
                + pelotas + " pelotas y " + palos + " palos. [DISPONIBLES → pelotas: " +
                pelotasDisponibles + ", palos: " + palosDisponibles + "]");
    }

    // Método sincronizado para devolver los recursos
    public synchronized void devolverMaterial(Jugador jugador) {
        int pelotas = jugador.getPelotasRequeridas();
        int palos = jugador.getPalosRequeridos();

        // Actualiza el estado del inventario
        pelotasDisponibles += pelotas;
        palosDisponibles += palos;

        // Mostrar devolución
        System.out.println("Jugador " + jugador.getId() + " (" + jugador.getTipo() + ") devuelve "
                + pelotas + " pelotas y " + palos + " palos. [DISPONIBLES → pelotas: " +
                pelotasDisponibles + ", palos: " + palosDisponibles + "]");

        notifyAll(); // Notifica a otros jugadores que podrían estar esperando
    }
}
package Problemas;

import java.util.concurrent.Semaphore;

public class Cajero {
    private final Semaphore semaforo = new Semaphore(1, true); // true para orden FIFO

    public void usarCajero(String nombreCliente) {
        try {
            semaforo.acquire(); // Exclusión mutua
            System.out.println("\n"+ nombreCliente + " usando el cajero");
            Thread.sleep(2000); // Simula el uso del cajero
            System.out.println(nombreCliente + " termina de usar el cajero");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaforo.release();
        }
    }
}
package Practica2;

import java.util.Random;

class Contador {
    private int sumaPares = 0;
    private int sumaImpares = 0;
    private final Object lock = new Object();
    private boolean turnoPar = true;

    public void sumarNumero(int num, boolean esPar, String nombreHilo) {
        synchronized (lock) {
            while (turnoPar != esPar) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            System.out.println(nombreHilo + " - " + (esPar ? "Par" : "Impar") + ": " + num);
            if (esPar) {
                sumaPares += num;
            } else {
                sumaImpares += num;
            }

            turnoPar = !turnoPar;
            lock.notifyAll();
        }
    }

    public void imprimirSumaTotal() {
        System.out.println("Suma total de pares: " + sumaPares);
        System.out.println("Suma total de impares: " + sumaImpares);
        System.out.println("Suma total de pares e impares: " + (sumaPares + sumaImpares));
    }
}

class HiloNumeros extends Thread {
    private final Contador contador;
    private final boolean esPar;
    private final Random random = new Random();

    public HiloNumeros(Contador contador, boolean esPar, String nombreHilo) {
        super(nombreHilo);
        this.contador = contador;
        this.esPar = esPar;
    }

    public boolean esPar(int num) {
        return num % 2 == 0;
    }

    @Override
    public void run() {
        for (int i = 0; i < 25; i++) {
            int num = random.nextInt(100) + 1;
            contador.sumarNumero(num, esPar, getName());
            try {
                Thread.sleep(500); // Simula procesamiento
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class NumerosSincronizadosThread {
    public static void main(String[] args) {
        Contador contador = new Contador();

        // Crear dos hilos con la misma clase, pero con configuraciones diferentes
        HiloNumeros hiloA = new HiloNumeros(contador, false, "Hilo A"); // Imprime impares
        HiloNumeros hiloB = new HiloNumeros(contador, true, "Hilo B");  // Imprime pares

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

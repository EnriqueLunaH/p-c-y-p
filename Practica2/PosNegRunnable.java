package Practica2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class Controlador {
    private boolean turnoPositivos = true; // Comienza con positivos
    private int sumaPositivos = 0;
    private int sumaNegativos = 0;
    private final List<Integer> numeros; // Lista de numeros generados aleatoriamente
    private int index = 0; // Indice para recorrer la lista de numeros

    public Controlador() {
        // Generar numeros aleatorios entre -100 y 100
        numeros = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 201; i++) {
            numeros.add(random.nextInt(201) - 100);
        }
        Collections.shuffle(numeros); // Mezclar los numeros aleatoriamente
    }

    public synchronized void procesarNumero(String nombre, boolean esPositivo) {
        while (index < numeros.size()) {
            while (turnoPositivos != esPositivo) { // Espera su turno
                try {
                    wait(); // Hilo espera hasta que sea su turno
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            if (index >= numeros.size()) {
                break;
            }

            int num = numeros.get(index);
            index++;

            // Verifica si el numero es del tipo que debe procesar el hilo
            if ((num >= 0 && esPositivo) || (num < 0 && !esPositivo)) {
                System.out.println(nombre + " identifico: " + num);
                if (esPositivo) {
                    sumaPositivos += num;
                } else {
                    sumaNegativos += num;
                }

                // Cambia el turno y notifica a los otros hilos
                turnoPositivos = !turnoPositivos;
                notifyAll(); // Notifica al otro hilo
            }
        }
        notifyAll(); // Asegura que el otro hilo no se quede esperando
    }

    public void imprimirSumaTotal() {
        System.out.println("Suma de numeros positivos: " + sumaPositivos);
        System.out.println("Suma de numeros negativos: " + sumaNegativos);
    }
}

class HiloNumeros implements Runnable {
    private final Controlador controlador;
    private final boolean esPositivo;
    private final String nombreHilo;

    public HiloNumeros(Controlador controlador, boolean esPositivo, String nombreHilo) {
        this.controlador = controlador;
        this.esPositivo = esPositivo;
        this.nombreHilo = nombreHilo;
    }

    @Override
    public void run() {
        controlador.procesarNumero(nombreHilo, esPositivo);
    }
}

public class PosNegRunnable {
    public static void main(String[] args) {
        Controlador controlador = new Controlador();

        // Crear dos hilos con la misma clase, pero con configuraciones diferentes
        Thread hiloPositivos = new Thread(new HiloNumeros(controlador, true, "Hilo Positivos"));
        Thread hiloNegativos = new Thread(new HiloNumeros(controlador, false, "Hilo Negativos"));

        // Iniciar hilos
        hiloPositivos.start();
        hiloNegativos.start();

        // Esperar a que los hilos terminen
        try {
            hiloPositivos.join();
            hiloNegativos.join();
        } catch (InterruptedException e) {
            System.out.println("Error en la sincronizacion de los hilos.");
        }

        // Imprimir la suma total
        controlador.imprimirSumaTotal();
    }
}

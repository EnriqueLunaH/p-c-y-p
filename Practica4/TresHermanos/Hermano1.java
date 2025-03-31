package Practica4.TresHermanos; // Declaramos el paquete

// Hilo que representa al Hermano 1
public class Hermano1 extends Thread {
    private final Monitor_Parcela parcela; // Referencia al monitor

    // Constructor que recibe el monitor compartido
    public Hermano1(Monitor_Parcela parcela) {
        this.parcela = parcela;
    }

    // Método principal del hilo
    public void run() {
        while (parcela.debeContinuar()) { // Se repite mientras haya árboles
            parcela.SiembraHermano_1();   // Intenta sembrar su árbol
        }
    }
}

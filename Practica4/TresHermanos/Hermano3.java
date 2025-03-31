package Practica4.TresHermanos;

// Hilo que representa al Hermano 3
public class Hermano3 extends Thread {
    private final Monitor_Parcela parcela;

    public Hermano3(Monitor_Parcela parcela) {
        this.parcela = parcela;
    }

    public void run() {
        while (parcela.debeContinuar()) {
            parcela.SiembraHermano_3();
        }
    }
}

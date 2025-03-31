package Practica4.TresHermanos;

// Hilo que representa al Hermano 2
public class Hermano2 extends Thread {
    private final Monitor_Parcela parcela;

    public Hermano2(Monitor_Parcela parcela) {
        this.parcela = parcela;
    }

    public void run() {
        while (parcela.debeContinuar()) {
            parcela.SiembraHermano_2();
        }
    }
}

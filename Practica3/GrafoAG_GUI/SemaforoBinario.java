package Practica3.GrafoAG_GUI;

public class SemaforoBinario {
    private int contador = 0;

    public SemaforoBinario(int valorInicial) {
        this.contador = valorInicial;
    }

    public synchronized void WAIT() {
        while (contador == 0) {
            try {
                wait();
            } catch (Exception e) {}
        }
        contador--;
    }

    public synchronized void SIGNAL() {
        contador = 1;
        notify();
    }
}
package Practica3.GrafoAG_GUI;

public class ProcesoG extends Thread {
    public void run() {
        Monitor.g.WAIT();
        Monitor.mutex.WAIT();
        Monitor.aviso[Monitor.i++] = "Soy el proceso G";
        Monitor.mutex.SIGNAL();
    }
}
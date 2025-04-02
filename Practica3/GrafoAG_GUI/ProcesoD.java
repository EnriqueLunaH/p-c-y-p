package Practica3.GrafoAG_GUI;

public class ProcesoD extends Thread {
    public void run() {
        Monitor.c.WAIT();
        Monitor.mutex.WAIT();
        Monitor.aviso[Monitor.i++] = "Soy el proceso D";
        Monitor.mutex.SIGNAL();
        Monitor.e.SIGNAL();
    }
}
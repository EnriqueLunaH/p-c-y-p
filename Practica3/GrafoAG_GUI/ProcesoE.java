package Practica3.GrafoAG_GUI;

public class ProcesoE extends Thread {
    public void run() {
        Monitor.d.WAIT();
        Monitor.e.WAIT();
        Monitor.mutex.WAIT();
        Monitor.aviso[Monitor.i++] = "Soy el proceso E";
        Monitor.mutex.SIGNAL();
        Monitor.f.SIGNAL();
        Monitor.g.SIGNAL();
    }
}
package Practica3.GrafoAG_GUI;

public class ProcesoC extends Thread {
    public void run() {
        Monitor.b.WAIT();
        Monitor.c.WAIT();
        Monitor.mutex.WAIT();
        Monitor.aviso[Monitor.i++] = "Soy el proceso C";
        Monitor.mutex.SIGNAL();
        Monitor.e.SIGNAL();
    }
}
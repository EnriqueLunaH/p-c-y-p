package Practica3.GrafoAG_GUI;

public class ProcesoA extends Thread {
    public void run() {
        Monitor.mutex.WAIT();
        Monitor.aviso[Monitor.i++] = "Soy el proceso A";
        Monitor.mutex.SIGNAL();
        Monitor.a.SIGNAL();
        Monitor.b.SIGNAL();
        Monitor.c.SIGNAL();
    }
}
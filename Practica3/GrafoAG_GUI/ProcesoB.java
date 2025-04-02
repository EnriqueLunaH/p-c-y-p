package Practica3.GrafoAG_GUI;

public class ProcesoB extends Thread {
    public void run() {
        Monitor.a.WAIT();
        Monitor.mutex.WAIT();
        Monitor.aviso[Monitor.i++] = "Soy el proceso B";
        Monitor.mutex.SIGNAL();
        Monitor.d.SIGNAL();
    }
}
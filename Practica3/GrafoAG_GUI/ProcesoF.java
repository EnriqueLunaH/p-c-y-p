package Practica3.GrafoAG_GUI;

public class ProcesoF extends Thread {
    public void run() {
        Monitor.f.WAIT();
        Monitor.mutex.WAIT();
        Monitor.aviso[Monitor.i++] = "Soy el proceso F";
        Monitor.mutex.SIGNAL();
    }
}
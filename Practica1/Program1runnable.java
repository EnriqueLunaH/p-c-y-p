package Practica1;

// Definir la clase ThreadUsingRunnable que implementa Runnable
class ThreadUsingRunnable implements Runnable {
    // Atributos
    String name;

    // Constructor de la clase ThreadUsingRunnable
    ThreadUsingRunnable(String name) {
        this.name = name;
    }

    // Método run que se ejecuta al llamar al método start
    @Override
    public void run() {
        System.out.println("Hilo " + name + " - Estado: " + Thread.currentThread().getState());
        try {
            Thread.sleep(2000); // Simula trabajo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Hilo " + name + " - Estado: " + Thread.currentThread().getState());
    }

    // Método para imprimir la información del hilo
    public void printInfo(Thread thread) {
        System.out.println("Thread name: " + thread.getName());
        System.out.println("ID: " + thread.getId());
        System.out.println("Priority: " + thread.getPriority());
        System.out.println("Alive: " + thread.isAlive());
        System.out.println("State: " + thread.getState());
    }
}

public class Program1runnable {
    // Método para imprimir la información de un hilo
    public static void printThreadInfo(ThreadUsingRunnable runnable, Thread thread) {
        runnable.printInfo(thread);
    }

    public static void main(String[] args) {

        // Crear dos instancias de la clase ThreadUsingRunnable
        ThreadUsingRunnable runnable1 = new ThreadUsingRunnable("T1");
        ThreadUsingRunnable runnable2 = new ThreadUsingRunnable("T2");
        ThreadUsingRunnable runnable3 = new ThreadUsingRunnable("T3");
        ThreadUsingRunnable runnable4 = new ThreadUsingRunnable("T4");

        Thread t1 = new Thread(runnable1);
        Thread t2 = new Thread(runnable2);
        Thread t3 = new Thread(runnable3);
        Thread t4 = new Thread(runnable4);

        // Imprimir estados antes de iniciar los hilos
        System.out.println("Estado de t1 antes de start(): " + t1.getState());
        System.out.println("Estado de t2 antes de start(): " + t2.getState());
        System.out.println("Estado de t3 antes de start(): " + t3.getState());
        System.out.println("Estado de t4 antes de start(): " + t4.getState());

        // Iniciar los hilos
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // Imprimir estados después de iniciar los hilos
        System.out.println("Estado de t1 después de start(): " + t1.getState());
        System.out.println("Estado de t2 después de start(): " + t2.getState());
        System.out.println("Estado de t3 después de start(): " + t3.getState());
        System.out.println("Estado de t4 después de start(): " + t4.getState());

        // esperar a que los hilos terminen
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        // Imprimir estados después de que los hilos terminen
        System.out.println("Estado de t1 después de join(): " + t1.getState());
        System.out.println("Estado de t2 después de join(): " + t2.getState());
        System.out.println("Estado de t3 después de join(): " + t3.getState());
        System.out.println("Estado de t4 después de join(): " + t4.getState());

        // Imprimir informacion de los hilos
        printThreadInfo(runnable1, t1);
        printThreadInfo(runnable2, t2);
        printThreadInfo(runnable3, t3);
        printThreadInfo(runnable4, t4);
    }
}
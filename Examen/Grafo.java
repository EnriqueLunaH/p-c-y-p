package Examen;

// Clase S1 extiende Thread (herencia)
class S1 extends Thread {
  public void run() {
    System.out.println("S1 iniciado");
    try { Thread.sleep(1000); } catch (InterruptedException e) {}
    System.out.println("S1 terminado");
  }
}

// Clase S2 extiende Thread (herencia)
class S2 extends Thread {
  public void run() {
    System.out.println("S2 iniciado");
    try { Thread.sleep(1000); } catch (InterruptedException e) {}
    System.out.println("S2 terminado");
  }
}

// Clase S3 implementa Runnable (interfaz)
class S3 implements Runnable {
  private Thread s1;
  public S3(Thread s1) { this.s1 = s1; }
  public void run() {
    try { s1.join(); } catch (InterruptedException e) {}
    System.out.println("S3 iniciado");
    try { Thread.sleep(1000); } catch (InterruptedException e) {}
    System.out.println("S3 terminado");
  }
}

// Clase S4 implementa Runnable (interfaz)
class S4 implements Runnable {
  private Thread s3, s2;
  public S4(Thread s3, Thread s2) { this.s3 = s3; this.s2 = s2; }
  public void run() {
    try { s3.join(); s2.join(); } catch (InterruptedException e) {}
    System.out.println("S4 iniciado");
    try { Thread.sleep(1000); } catch (InterruptedException e) {}
    System.out.println("S4 terminado");
  }
}

// Clase S5 implementa Runnable (interfaz)
class S5 implements Runnable {
  private Thread s3;
  public S5(Thread s3) { this.s3 = s3; }
  public void run() {
    try { s3.join(); } catch (InterruptedException e) {}
    System.out.println("S5 iniciado");
    try { Thread.sleep(1000); } catch (InterruptedException e) {}
    System.out.println("S5 terminado");
  }
}

// Clase S6 implementa Runnable (interfaz)
class S6 implements Runnable {
  private Thread s4, s5;
  public S6(Thread s4, Thread s5) { this.s4 = s4; this.s5 = s5; }
  public void run() {
    try { s4.join(); s5.join(); } catch (InterruptedException e) {}
    System.out.println("S6 iniciado");
    try { Thread.sleep(1000); } catch (InterruptedException e) {}
    System.out.println("S6 terminado");
  }
}

public class Grafo {
  public static void main(String[] args) {
    S1 s1 = new S1();
    S2 s2 = new S2();
    Thread t3 = new Thread(new S3(s1));
    Thread t4 = new Thread(new S4(t3, s2));
    Thread t5 = new Thread(new S5(t3));
    Thread t6 = new Thread(new S6(t4, t5));
    
    s1.start();
    s2.start();
    t3.start();
    t4.start();
    t5.start();
    t6.start();
  }
}

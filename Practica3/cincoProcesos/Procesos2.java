package Practica3.cincoProcesos;

// Clase base que define los semáforos compartidos entre procesos
public class Procesos2 {
  // Semáforos de sincronización entre instrucciones
  protected static final SemaforoBinario A = new SemaforoBinario(0);
  protected static final SemaforoBinario B = new SemaforoBinario(0);
  protected static final SemaforoBinario C = new SemaforoBinario(0);
  protected static final SemaforoBinario D = new SemaforoBinario(0);

  // Semáforo para proteger el uso de la consola (exclusión mutua)
  protected static final SemaforoBinario mutex = new SemaforoBinario(1);
}
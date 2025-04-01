package Practica3.sieteProcesosThread;

// Clase base que contiene todos los semáforos compartidos entre procesos
public class ProcesosBase {
  protected static final SemaforoBinario a = new SemaforoBinario(0);
  protected static final SemaforoBinario b = new SemaforoBinario(0);
  protected static final SemaforoBinario c = new SemaforoBinario(0);
  protected static final SemaforoBinario d = new SemaforoBinario(0);
  protected static final SemaforoBinario e = new SemaforoBinario(0);
  protected static final SemaforoBinario f = new SemaforoBinario(0);
  protected static final SemaforoBinario g = new SemaforoBinario(0);
  
  // Semáforo para proteger la impresión en consola (exclusión mutua)
  protected static final SemaforoBinario mutex = new SemaforoBinario(1);
}
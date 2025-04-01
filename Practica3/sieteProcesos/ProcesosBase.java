package Practica3.sieteProcesos;

// Clase base que contiene los semáforos compartidos entre procesos
public class ProcesosBase {
  // Semáforos para transiciones del grafo
  protected static final SemaforoBinario a = new SemaforoBinario(0);
  protected static final SemaforoBinario b = new SemaforoBinario(0);
  protected static final SemaforoBinario c = new SemaforoBinario(0);
  protected static final SemaforoBinario d = new SemaforoBinario(0);
  protected static final SemaforoBinario e = new SemaforoBinario(0);
  protected static final SemaforoBinario f = new SemaforoBinario(0);
  protected static final SemaforoBinario g = new SemaforoBinario(0);

  // Semáforo para exclusión mutua en la consola
  protected static final SemaforoBinario mutex = new SemaforoBinario(1);
}
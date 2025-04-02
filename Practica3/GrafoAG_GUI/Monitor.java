package Practica3.GrafoAG_GUI;

public class Monitor {
    protected static final SemaforoBinario a = new SemaforoBinario(0);
    protected static final SemaforoBinario b = new SemaforoBinario(0);
    protected static final SemaforoBinario c = new SemaforoBinario(0);
    protected static final SemaforoBinario d = new SemaforoBinario(0);
    protected static final SemaforoBinario e = new SemaforoBinario(0);
    protected static final SemaforoBinario f = new SemaforoBinario(0);
    protected static final SemaforoBinario g = new SemaforoBinario(0);
    protected static final SemaforoBinario mutex = new SemaforoBinario(1);
    public static int i = 0;
    public static String[] aviso = new String[7];
}
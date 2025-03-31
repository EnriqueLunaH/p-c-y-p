package Practica4.TresHermanos; // Paquete del monitor

// Monitor que controla el acceso sincronizado a la parcela
public class Monitor_Parcela {
    private int arbolesSembrados = 0; // Contador de árboles sembrados
    private final int totalArboles;   // Total de árboles a sembrar
    private int turnoActual = 1;      // Turno de siembra: 1, 2 o 3

    // Constructor que recibe cuántos árboles se sembrarán en total
    public Monitor_Parcela(int totalArboles) {
        this.totalArboles = totalArboles;
    }

    // Indica si aún hay árboles pendientes por sembrar
    public synchronized boolean debeContinuar() {
        return arbolesSembrados < totalArboles;
    }

    // Método sincronizado para el Hermano 1
    public synchronized void SiembraHermano_1() {
        // Espera mientras no sea su turno y aún falten árboles
        while (turnoActual != 1 && debeContinuar()) {
            try {
                wait(); // Libera el monitor y espera
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt(); // Restablece el estado de interrupción
            }
        }

        if (!debeContinuar()) return; // Verificación final por seguridad

        arbolesSembrados++; // Aumenta el contador
        System.out.println(Thread.currentThread().getName() + ": Hermano 1 siembra el arbol: " + arbolesSembrados);
        dormir(); // Simula el tiempo de siembra

        turnoActual = 2; // Cambia el turno al Hermano 2
        notifyAll(); // Despierta a los demás hilos
    }

    // Método sincronizado para el Hermano 2
    public synchronized void SiembraHermano_2() {
        while (turnoActual != 2 && debeContinuar()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

        if (!debeContinuar()) return;

        arbolesSembrados++;
        System.out.println(Thread.currentThread().getName() + ": Hermano 2 siembra el arbol: " + arbolesSembrados);
        dormir();

        turnoActual = 3; // Turno para el Hermano 3
        notifyAll();
    }

    // Método sincronizado para el Hermano 3
    public synchronized void SiembraHermano_3() {
        while (turnoActual != 3 && debeContinuar()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

        if (!debeContinuar()) return;

        arbolesSembrados++;
        System.out.println(Thread.currentThread().getName() + ": Hermano 3 siembra el arbol: " + arbolesSembrados);
        dormir();

        turnoActual = 1; // Regresa el turno al Hermano 1
        notifyAll();
    }

    // Método auxiliar para simular el tiempo de siembra
    private void dormir() {
        try {
            Thread.sleep(500); // 500 ms por árbol
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}

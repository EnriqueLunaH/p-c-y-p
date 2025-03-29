package Practica3;

class Paciente implements Runnable {
  // El ID del paciente
  private final int id;

  // Instancias de los manejadores de consultorios y turnos

  private final ConsultorioManager consultorios;

  // Instancia del manejador de turnos
  // Este manejador controla el orden en que los pacientes son atendidos
  private final TurnoManager turnos;

  // Constructor: recibe ID del paciente y las referencias necesarias para sincronización
  public Paciente(int id, ConsultorioManager consultorios, TurnoManager turnos) {
    this.id = id;
    this.consultorios = consultorios;
    this.turnos = turnos;
  }

  // Simula la atención inicial por parte de la enfermera
  private void atencionEnfermera() throws InterruptedException {
    System.out.println("Paciente " + id + " está siendo atendido por la enfermera.");
    Thread.sleep(500); // Pausa el hilo para simular el tiempo de atención
    System.out.println("Paciente " + id + " terminó con la enfermera.");
  }

  // Simula la atención médica posterior al consultorio
  private void atencionMedico() throws InterruptedException {
    System.out.println("Paciente " + id + " está siendo atendido por el médico.");
    Thread.sleep(700); // Pausa el hilo para simular el tiempo de consulta médica
    System.out.println("Paciente " + id + " terminó con el médico.");
  }

  // Método run que se ejecuta cuando el hilo del paciente inicia
  @Override
  public void run() {
    try {
      // Espera hasta que sea el turno del paciente
      turnos.esperarTurno(id);
      
      // Atención con la enfermera
      atencionEnfermera();
      
      // Solicita acceso a un consultorio
      consultorios.ingresarConsultorio("Paciente " + id);
      Thread.sleep(1000);
      consultorios.salirConsultorio("Paciente " + id);
      
      // Atención con el médico
      atencionMedico();
      
      // // Mensaje final de atención completa
      System.out.println("Paciente " + id + " ha terminado su atención.\n");

      // Permite que el siguiente paciente continúe
      turnos.siguienteTurno();

    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      System.out.println("Paciente " + id + " fue interrumpido.");
    }
  }
}
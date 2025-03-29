package Practica3;

public class PacientesSemaforo {
  public static void main(String[] args) {
    int numConsultorios = 1;
    int numPacientes = 5;

    ConsultorioManager consultorios = new ConsultorioManager(numConsultorios);
    TurnoManager turnos = new TurnoManager();

    for (int i = 1; i <= numPacientes; i++) {
      Thread hiloPaciente = new Thread(new Paciente(i, consultorios, turnos));
      hiloPaciente.start();
    }
  }
}

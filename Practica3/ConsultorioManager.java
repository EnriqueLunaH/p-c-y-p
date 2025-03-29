package Practica3;
import java.util.concurrent.Semaphore;

class ConsultorioManager {
  // Semáforo para controlar el acceso a los consultorios
  private final Semaphore consultorios;

  // Constructor que inicializa el semáforo con el número de consultorios disponibles
  public ConsultorioManager(int numConsultorios) {
    this.consultorios = new Semaphore(numConsultorios, true);
  }
  
  // Método para que un paciente ingrese a un consultorio
  // Si no hay consultorios disponibles, el paciente esperará hasta que uno se libere
  public void ingresarConsultorio(String nombrePaciente) throws InterruptedException {
    consultorios.acquire();
    System.out.println(nombrePaciente + " está usando el consultorio.");
  }

  // Método para que un paciente salga de un consultorio
  // Libera el semáforo para que otro paciente pueda ingresar
  public void salirConsultorio(String nombrePaciente) {
    System.out.println(nombrePaciente + " terminó en el consultorio.");
    consultorios.release();
  }
}
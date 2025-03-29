package ADS;

import java.io.*;
import java.util.*;

class Alumno implements Serializable {
    private static final long serialVersionUID = 1L;
    String nombre, apellidoPaterno, apellidoMaterno, carrera;
    int semestre, edad;
    String matricula;
    double promedio;

    public Alumno(String nombre, String apellidoPaterno, String apellidoMaterno, String carrera, int semestre, String matricula, double promedio, int edad) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.carrera = carrera;
        this.semestre = semestre;
        this.matricula = matricula;
        this.promedio = promedio;
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Matricula: " + matricula + ", Nombre: " + nombre + " " + apellidoPaterno + " " + apellidoMaterno +
               ", Carrera: " + carrera + ", Semestre: " + semestre + ", Promedio: " + promedio + ", Edad: " + edad;
    }
}

public class form {
    private static final String FILE_NAME = "alumnos.dat";
    private static List<Alumno> alumnos = new ArrayList<>();

    public static void main(String[] args) {
        cargarDatos();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Crear Alumno");
            System.out.println("2. Buscar Alumno");
            System.out.println("3. Actualizar Alumno");
            System.out.println("4. Eliminar Alumno");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> crearAlumno(scanner);
                case 2 -> buscarAlumno(scanner);
                case 3 -> actualizarAlumno(scanner);
                case 4 -> eliminarAlumno(scanner);
                case 5 -> guardarDatos();
                default -> System.out.println("Opcion invalida.");
            }
        } while (opcion != 5);
    }

    private static void crearAlumno(Scanner scanner) {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido Paterno: ");
        String apellidoPaterno = scanner.nextLine();
        System.out.print("Apellido Materno: ");
        String apellidoMaterno = scanner.nextLine();
        System.out.print("Carrera: ");
        String carrera = scanner.nextLine();
        System.out.print("Semestre: ");
        int semestre = scanner.nextInt();
        System.out.print("Matricula (9 digitos): ");
        String matricula = scanner.next();
        System.out.print("Promedio: ");
        double promedio = scanner.nextDouble();
        System.out.print("Edad: ");
        int edad = scanner.nextInt();

        alumnos.add(new Alumno(nombre, apellidoPaterno, apellidoMaterno, carrera, semestre, matricula, promedio, edad));
        System.out.println("Alumno creado exitosamente.");
    }

    private static void buscarAlumno(Scanner scanner) {
        System.out.println("\nBuscar por:");
        System.out.println("1. Matricula");
        System.out.println("2. Semestre");
        System.out.println("3. Promedio");
        System.out.println("4. Salir");
        System.out.print("Selecciona una opcion: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        switch (opcion) {
            case 1 -> {
                System.out.print("Matricula: ");
                String matricula = scanner.nextLine();
                alumnos.stream()
                       .filter(a -> a.matricula.equals(matricula))
                       .forEach(System.out::println);
            }
            case 2 -> {
                System.out.print("Semestre: ");
                int semestre = scanner.nextInt();
                alumnos.stream()
                       .filter(a -> a.semestre == semestre)
                       .forEach(System.out::println);
            }
            case 3 -> {
                System.out.print("Promedio minimo: ");
                double promedio = scanner.nextDouble();
                alumnos.stream()
                       .filter(a -> a.promedio >= promedio)
                       .forEach(System.out::println);
            }
            case 4 -> System.out.println("Saliendo del submenu de busqueda.");
            default -> System.out.println("Opcion invalida.");
        }
    }

    private static void actualizarAlumno(Scanner scanner) {
        System.out.print("Matricula del alumno a actualizar: ");
        String matricula = scanner.nextLine();
        Alumno alumno = alumnos.stream()
                               .filter(a -> a.matricula.equals(matricula))
                               .findFirst()
                               .orElse(null);

        if (alumno == null) {
            System.out.println("Alumno no encontrado.");
            return;
        }

        System.out.println("\nActualizar:");
        System.out.println("1. Nombre");
        System.out.println("2. Apellido Paterno");
        System.out.println("3. Apellido Materno");
        System.out.println("4. Carrera");
        System.out.println("5. Semestre");
        System.out.println("6. Matricula");
        System.out.println("7. Promedio");
        System.out.println("8. Edad");
        System.out.print("Selecciona una opcion: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        switch (opcion) {
            case 1 -> {
                System.out.print("Nuevo Nombre: ");
                alumno.nombre = scanner.nextLine();
            }
            case 2 -> {
                System.out.print("Nuevo Apellido Paterno: ");
                alumno.apellidoPaterno = scanner.nextLine();
            }
            case 3 -> {
                System.out.print("Nuevo Apellido Materno: ");
                alumno.apellidoMaterno = scanner.nextLine();
            }
            case 4 -> {
                System.out.print("Nueva Carrera: ");
                alumno.carrera = scanner.nextLine();
            }
            case 5 -> {
                System.out.print("Nuevo Semestre: ");
                alumno.semestre = scanner.nextInt();
            }
            case 6 -> {
                System.out.print("Nueva Matricula (9 digitos): ");
                alumno.matricula = scanner.next();
            }
            case 7 -> {
                System.out.print("Nuevo Promedio: ");
                alumno.promedio = scanner.nextDouble();
            }
            case 8 -> {
                System.out.print("Nueva Edad: ");
                alumno.edad = scanner.nextInt();
            }
            default -> System.out.println("Opcion invalida.");
        }
        System.out.println("Alumno actualizado exitosamente.");
    }

    private static void eliminarAlumno(Scanner scanner) {
        System.out.print("Matricula del alumno a eliminar: ");
        String matricula = scanner.nextLine();
        if (alumnos.removeIf(a -> a.matricula.equals(matricula))) {
            System.out.println("Alumno eliminado exitosamente.");
        } else {
            System.out.println("Alumno no encontrado.");
        }
    }

    private static void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(alumnos);
            System.out.println("Datos guardados exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    private static void cargarDatos() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                alumnos = (List<Alumno>) ois.readObject();
                System.out.println("Datos cargados exitosamente.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error al cargar los datos: " + e.getMessage());
            }
        }
    }
}

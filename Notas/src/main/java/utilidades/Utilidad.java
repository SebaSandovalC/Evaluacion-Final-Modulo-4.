package utilidades;

import java.util.Scanner;

import modelos.Alumno;
import servicios.AlumnoServicio;

public class Utilidad {
    public static Alumno crearAlumno(Scanner scanner, String rut) {
        System.out.println("Ingrese nombre: ");
        scanner.nextLine();
        System.out.println("Ingrese apellido: ");
        scanner.nextLine();
        System.out.println("Ingrese dirección: ");
        scanner.nextLine();

        return new Alumno();
    }

    public static void agregarMateria(Scanner scanner, AlumnoServicio servicio, String rut) {
        System.out.println("Seleccione materia:");
        
    }

    public static void agregarNota(Scanner scanner, AlumnoServicio servicio, String rut) {
        System.out.print("Ingrese la materia: ");
        
    }
}

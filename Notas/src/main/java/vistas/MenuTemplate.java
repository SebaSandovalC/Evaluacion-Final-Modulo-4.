package vistas;

import java.util.Scanner;

public abstract class MenuTemplate {
    protected Scanner scanner = new Scanner(System.in);

    public void iniciarMenu() {
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();

            int opcion = -1;
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número. Intente nuevamente.");
                continue;
            }

            switch (opcion) {
                case 1:
                    exportarDatos();
                    break;
                case 2:
                    crearAlumno();
                    break;
                case 3:
                    agregarMateria();
                    break;
                case 4:
                    agregarNotaPasoUno();
                    break;
                case 5:
                    listarAlumnos();
                    break;
                case 6:
                    terminarPrograma();
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("Seleccione una opción:");
        System.out.println("1. Exportar datos");
        System.out.println("2. Crear Alumno");
        System.out.println("3. Agregar materia a alumno");
        System.out.println("4. Agregar nota a materia");
        System.out.println("5. Listar Alumnos");
        System.out.println("6. Salir");
    }

    protected abstract void listarAlumnos();

    protected abstract void agregarNotaPasoUno();

    protected abstract void crearAlumno();

    protected abstract void agregarMateria();

    protected abstract void exportarDatos();

    protected abstract void terminarPrograma();
}

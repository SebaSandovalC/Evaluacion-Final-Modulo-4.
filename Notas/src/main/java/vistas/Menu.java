package vistas;

import java.util.ArrayList;
import java.util.List;

import modelos.Alumno;
import modelos.Materia;
import modelos.MateriaEnum;
import servicios.AlumnoServicio;
import servicios.ArchivoServicio;
import servicios.PromedioServicioImp;

public class Menu extends MenuTemplate {
    private AlumnoServicio alumnoServicio = new AlumnoServicio();
    private ArchivoServicio archivoServicio = new ArchivoServicio(new PromedioServicioImp());

    @Override
    protected void listarAlumnos() {
        if (alumnoServicio.listarAlumnos().isEmpty()) {
            System.out.println("No hay alumnos registrados.");
        } else {
            
            alumnoServicio.listarAlumnos().forEach((rut, alumno) -> {
                System.out.println("RUT: " + rut);
                System.out.println("Nombre: " + alumno.getNombre());
                System.out.println("Apellido: " + alumno.getApellido());
                System.out.println("Dirección: " + alumno.getDireccion());

                
                if (alumno.getMaterias().isEmpty()) {
                    System.out.println("Este alumno no tiene materias asignadas.");
                } else {
                    System.out.println("Materias asignadas:");
                    for (Materia materia : alumno.getMaterias()) {
                        System.out.print(materia.getNombre() + ": ");
                        if (materia.getNotas().isEmpty()) {
                            System.out.println("No hay notas asignadas.");
                        } else {
                            System.out.println("Notas: " + materia.getNotas());
                        }
                    }
                }
                System.out.println("===================================");
            });
        }
    }



    @Override
    protected void agregarNotaPasoUno() {
        System.out.println("Lista de alumnos registrados:");
        listarAlumnos(); 

        System.out.print("Ingrese el RUT del alumno: ");
        String rut = scanner.nextLine();

        
        if (alumnoServicio.listarAlumnos().containsKey(rut)) {
            System.out.println("Seleccione la materia para agregar la nota:");

            
            List<Materia> materias = alumnoServicio.materiasPorAlumnos(rut);
            if (materias.isEmpty()) {
                System.out.println("El alumno no tiene materias asignadas.");
                return;
            }

            
            for (int i = 0; i < materias.size(); i++) {
                System.out.println((i + 1) + ". " + materias.get(i).getNombre());
            }

            
            int opcionMateria;
            try {
                opcionMateria = Integer.parseInt(scanner.nextLine());
                if (opcionMateria > 0 && opcionMateria <= materias.size()) {
                    Materia materiaSeleccionada = materias.get(opcionMateria - 1);

                    
                    System.out.print("Ingrese la nota para la materia " + materiaSeleccionada.getNombre() + ": ");
                    double nota = scanner.nextDouble();
                    scanner.nextLine(); 

                    
                    materiaSeleccionada.getNotas().add(nota);
                    System.out.println("Nota " + nota + " agregada a la materia " + materiaSeleccionada.getNombre() + " del alumno con RUT: " + rut);
                } else {
                    System.out.println("Opción de materia no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido para seleccionar la materia.");
            }
        } else {
            System.out.println("Alumno no encontrado.");
        }
    }


    @Override
    protected void crearAlumno() {
        System.out.print("Ingrese el RUT del alumno: ");
        String rut = scanner.nextLine();
        System.out.print("Ingrese el nombre del alumno: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido del alumno: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese la dirección del alumno: ");
        String direccion = scanner.nextLine();

     
        Alumno nuevoAlumno = new Alumno();
        nuevoAlumno.setRut(rut);
        nuevoAlumno.setNombre(nombre);
        nuevoAlumno.setApellido(apellido);
        nuevoAlumno.setDireccion(direccion);
        nuevoAlumno.setMaterias(new ArrayList<>());

        
        alumnoServicio.crearAlumno(nuevoAlumno);
    }

    @Override
    protected void agregarMateria() {
        System.out.println("Lista de alumnos registrados:");
        listarAlumnos(); 

        System.out.print("Ingrese el RUT del alumno: ");
        String rut = scanner.nextLine();

        
        if (alumnoServicio.listarAlumnos().containsKey(rut)) {
            System.out.println("Seleccione la materia para asignar al alumno:");

            
            for (MateriaEnum materiaEnum : MateriaEnum.values()) {
                System.out.println(materiaEnum.ordinal() + 1 + ". " + materiaEnum);
            }

            int opcionMateria;
            try {
                opcionMateria = Integer.parseInt(scanner.nextLine());
                if (opcionMateria > 0 && opcionMateria <= MateriaEnum.values().length) {
                    MateriaEnum materiaSeleccionada = MateriaEnum.values()[opcionMateria - 1];
                    Materia materia = new Materia(materiaSeleccionada);

                    
                    alumnoServicio.agregarMateria(rut, materia);

                    System.out.println("Materia " + materiaSeleccionada + " asignada al alumno con RUT: " + rut);
                } else {
                    System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        } else {
            System.out.println("Alumno no encontrado.");
        }
    }




    @Override
    protected void exportarDatos() {
        
        archivoServicio.exportarDatos(alumnoServicio.listarAlumnos(), "promedios.txt");
        System.out.println("Los datos han sido exportados exitosamente.");
    }


    @Override
    protected void terminarPrograma() {
        System.out.println("Saliendo del programa...");
    }
}

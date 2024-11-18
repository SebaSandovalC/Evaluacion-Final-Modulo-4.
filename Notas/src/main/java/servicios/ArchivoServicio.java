package servicios;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import modelos.Alumno;
import modelos.Materia;

public class ArchivoServicio {

    public ArchivoServicio(PromedioServicioImp promedioServicioImp) {

	}

	public void exportarDatos(Map<String, Alumno> alumnos, String rutaArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Alumno alumno : alumnos.values()) {
                for (Materia materia : alumno.getMaterias()) {
                    double promedio = calcularPromedio(materia.getNotas());

                    
                    writer.write("Alumno: " + alumno.getRut() + " - " + alumno.getNombre() + "\n");
                    writer.write("Materia: " + materia.getNombre() + " - Promedio: " + promedio + "\n");
                    writer.write("=============================\n");
                }
            }
            System.out.println("Datos exportados correctamente a " + rutaArchivo);
        } catch (IOException e) {
            System.out.println("Ocurrió un error al exportar los datos: " + e.getMessage());
        }
    }


    public double calcularPromedio(List<Double> notas) {
        if (notas.isEmpty()) {
            return 0;
        }
        double suma = 0;
        for (Double nota : notas) {
            suma += nota;
        }
        return suma / notas.size();
    }
}


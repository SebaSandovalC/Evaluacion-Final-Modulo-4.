package servicios;

import modelos.Alumno;
import modelos.Materia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlumnoServicio {
    private Map<String, Alumno> listaAlumnos;

    public AlumnoServicio() {
        this.listaAlumnos = new HashMap<>(); 
    }

   
    public void crearAlumno(Alumno alumno) {
        listaAlumnos.put(alumno.getRut(), alumno);
    }

    public void agregarMateria(String rutAlumno, Materia materia) {
        Alumno alumno = listaAlumnos.get(rutAlumno);
        if (alumno != null) {
            alumno.getMaterias().add(materia);
        }
    }

    public Map<String, Alumno> listarAlumnos() {
        return listaAlumnos; 
    }

    public List<Materia> materiasPorAlumnos(String rutAlumno) {
        Alumno alumno = listaAlumnos.get(rutAlumno);
        if (alumno != null) {
            return alumno.getMaterias(); 
        }
        return new ArrayList<>(); 
    }


	public void crearAlummno(Alumno alumno) {
		// TODO Auto-generated method stub
		
	}
}

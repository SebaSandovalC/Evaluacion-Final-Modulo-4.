package servicios;

import modelos.Alumno;
import modelos.Materia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlumnoServicioImp extends AlumnoServicio {
    private Map<String, Alumno> alumnos;

    public AlumnoServicioImp() {
        this.alumnos = new HashMap<>();
    }

    @Override
    public void crearAlummno(Alumno alumno) {
        if (!alumnos.containsKey(alumno.getRut())) {
            alumnos.put(alumno.getRut(), alumno);
        }
    }

    @Override
    public void agregarMateria(String rutAlumno, Materia materia) {
        Alumno alumno = alumnos.get(rutAlumno);
        if (alumno != null) {
            List<Materia> materias = alumno.getMaterias();
            if (materias == null) {
                materias = new ArrayList<>(); 
                alumno.setMaterias(materias);
            }
            if (!materias.contains(materia)) {
                materias.add(materia);
            }
        } else {
            throw new IllegalArgumentException("Alumno con RUT " + rutAlumno + " no encontrado");
        }
    }


    @Override
    public List<Materia> materiasPorAlumnos(String rut) {
        Alumno alumno = alumnos.get(rut);
        return (alumno != null) ? alumno.getMaterias() : new ArrayList<>();
    }

    @Override
    public Map<String, Alumno> listarAlumnos() {
        return this.alumnos;
    }
}


package test;

import modelos.Alumno;
import modelos.Materia;
import modelos.MateriaEnum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servicios.AlumnoServicioImp;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class AlumnoServicioTest {
    private AlumnoServicioImp alumnoServicio;
    
	@BeforeEach
    void setup() {
        alumnoServicio = new AlumnoServicioImp(); 
    }

    @Test
    void crearAlumnoTest() {
        Alumno alumno = new Alumno();
        alumno.setRut("1.111.111-1");
        alumno.setNombre("Milton");
        alumno.setApellido("P.");
        alumno.setDireccion("Casita 1");

        alumnoServicio.crearAlummno(alumno);

        Map<String, Alumno> alumnos = alumnoServicio.listarAlumnos();
        assertNotNull(alumnos, "La lista de alumnos no debería ser null");
        assertEquals(1, alumnos.size(), "Debería haber exactamente un alumno registrado");
        assertEquals(alumno, alumnos.get("1.111.111-1"), "El alumno registrado debería ser Milton");
    }


    @Test
    void agregarMateriaTest() {
        Alumno alumno = new Alumno();
        alumno.setRut("1.111.111-1");
        alumno.setNombre("Milton");

        alumnoServicio.crearAlummno(alumno);

        Materia matematicas = new Materia(null);
        matematicas.setNombre(MateriaEnum.MATEMATICAS);

        alumnoServicio.agregarMateria(alumno.getRut(), matematicas);

        List<Materia> materias = alumnoServicio.materiasPorAlumnos(alumno.getRut());
        assertNotNull(materias, "La lista de materias no debería ser null");
        assertEquals(1, materias.size(), "El alumno debería tener una materia registrada");
        assertEquals(matematicas, materias.get(0), "La materia registrada debería ser Matemáticas");
    }


    @Test
    void materiasPorAlumnosTest() {
        Alumno alumno = new Alumno();
        alumno.setRut("1.111.111-1");
        alumno.setNombre("Milton");

        alumnoServicio.crearAlummno(alumno);

        Materia matematicas = new Materia(null);
        matematicas.setNombre(MateriaEnum.MATEMATICAS);

        Materia lenguaje = new Materia(null);
        lenguaje.setNombre(MateriaEnum.LENGUAJE);

        alumnoServicio.agregarMateria(alumno.getRut(), matematicas);
        alumnoServicio.agregarMateria(alumno.getRut(), lenguaje);

        List<Materia> materias = alumnoServicio.materiasPorAlumnos(alumno.getRut());
        assertNotNull(materias, "La lista de materias no debería ser null");
        assertEquals(2, materias.size(), "El alumno debería tener dos materias registradas");
        assertTrue(materias.contains(matematicas), "La lista de materias debería contener Matemáticas");
        assertTrue(materias.contains(lenguaje), "La lista de materias debería contener Lenguaje");
    }


    @Test
    void listarAlumnosTest() {
        Alumno alumno1 = new Alumno();
        alumno1.setRut("1.111.111-1");
        alumno1.setNombre("Milton");

        Alumno alumno2 = new Alumno();
        alumno2.setRut("2.222.222-2");
        alumno2.setNombre("Sara");

        alumnoServicio.crearAlummno(alumno1);
        alumnoServicio.crearAlummno(alumno2);

        Map<String, Alumno> alumnos = alumnoServicio.listarAlumnos();
        assertNotNull(alumnos, "El mapa de alumnos no debería ser null");
        assertEquals(2, alumnos.size(), "Debería haber dos alumnos registrados");
        assertEquals(alumno1, alumnos.get("1.111.111-1"), "El alumno con RUT 1.111.111-1 debería ser Milton");
        assertEquals(alumno2, alumnos.get("2.222.222-2"), "El alumno con RUT 2.222.222-2 debería ser Sara");
    }

}


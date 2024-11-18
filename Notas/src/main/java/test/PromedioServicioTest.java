package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servicios.PromedioServicioImp;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PromedioServicioTest {

    private PromedioServicioImp promedioServicio;

    @BeforeEach
    public void setUp() {
        promedioServicio = new PromedioServicioImp();
    }

    @Test
    public void calcularPromedioConNotas() {
        List<Double> notas = Arrays.asList(4.0, 5.0, 6.0);
        double resultado = promedioServicio.calcularPromedio(notas);
        assertEquals(5.0, resultado, "El promedio debería ser 5.0");
    }

    @Test
    public void calcularPromedioConUnaNota() {
        List<Double> notas = Arrays.asList(7.0);
        double resultado = promedioServicio.calcularPromedio(notas);
        assertEquals(7.0, resultado, "El promedio debería ser 7.0");
    }

    @Test
    public void calcularPromedioConListaVacia() {
        List<Double> notas = Arrays.asList();
        double resultado = promedioServicio.calcularPromedio(notas);
        assertEquals(0.0, resultado, "El promedio debería ser 0.0 para una lista vacía");
    }
}



package es.unican.is2;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FranquiciasITest {
	
	private Tienda t;
    private Empleado emp1;
    private Empleado emp2;

    @BeforeEach
    public void setUp() {
        Tienda tAux = new Tienda(); // Uso del constructor vacio para completar cobertura
        t = new Tienda("NombreTienda", "DirecciónTienda");
        emp1 = new Empleado("22222222A", "Pedro", Categoria.VENDEDOR, LocalDate.now().minusYears(8));
        emp2 = new Empleado("22222222B", "Lucia", Categoria.ENCARGADO, LocalDate.now().minusYears(15));

        // Anhadir empleados a la tienda
        t.getEmpleados().add(emp1);
        t.getEmpleados().add(emp2);
    }

    @Test
    public void testGastoMensualSueldos() {
        // Calcular el gasto mensual y verificar que sea el esperado
        double gastoMensual = t.gastoMensualSueldos();
        assertEquals(3650.0, gastoMensual, 0.01); // 2100 + 1550
    }

    @Test
    public void testBuscaEmpleadoExistente() {
        // Buscar un empleado y verificar que existe
        Empleado empleadoEncontrado = t.buscaEmpleado("22222222A");
        assertNotNull(empleadoEncontrado);
        assertEquals("Pedro", empleadoEncontrado.getNombre());
    }

    @Test
    public void testBuscaEmpleadoNoExistente() {
        // Buscar un empleado y verificar que no exista
        Empleado empleadoNoEncontrado = t.buscaEmpleado("33333333A");
        assertNull(empleadoNoEncontrado);
    }

    // Tests auxiliares para comprobar el funcionamiento del resto de metodos
    
    @Test
    
    public void testConstructorTienda() {
        assertNotNull(t);
    }

    @Test
    public void testSetNombre() {
        String nuevoNombre = "NuevoNombreTienda";
        t.setNombre(nuevoNombre);
        assertEquals(nuevoNombre, t.getNombre());
    }

    @Test
    public void testSetDireccion() {
        String nuevaDireccion = "NuevaDirecciónTienda";
        t.setDireccion(nuevaDireccion);
        assertEquals(nuevaDireccion, t.getDireccion());
    }

    @Test
    public void testSetId() {
        long nuevoId = 4;
        t.setId(nuevoId);
        assertEquals(nuevoId, t.getId());
    } 

    @Test
    public void testGetEmpleados() {
        List<Empleado> empleadosDistintos = new ArrayList<>();
        List<Empleado> empleadosIguales = new ArrayList<>();
        Empleado empleado1 = new Empleado("12345678A", "Lucas", Categoria.VENDEDOR, LocalDate.now());
        Empleado empleado2 = new Empleado("12345678B", "Manuel", Categoria.AUXILIAR, LocalDate.now());
        
        Empleado emp1 = new Empleado("22222222A", "Pedro", Categoria.VENDEDOR, LocalDate.now().minusYears(8));
        Empleado emp2 = new Empleado("22222222B", "Lucia", Categoria.ENCARGADO, LocalDate.now().minusYears(15));
       
        empleadosDistintos.add(empleado1);
        empleadosDistintos.add(empleado2);

        empleadosIguales.add(emp1);
        empleadosIguales.add(emp2);
        
        assertEquals(empleadosIguales, t.getEmpleados());
        assertNotEquals(empleadosDistintos, t.getEmpleados());
    }

}

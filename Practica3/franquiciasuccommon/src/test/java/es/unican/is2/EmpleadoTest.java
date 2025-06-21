package es.unican.is2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.IllegalFormatException;

public class EmpleadoTest {
	
	// Definir empleados estáticos
    static Empleado emp1;
    static Empleado emp2; 
    static Empleado emp3;

    @BeforeAll
    public static void beforeAll() throws Exception {
    	emp1 = new Empleado("12345678A", "Javier", Categoria.ENCARGADO, LocalDate.now().minusYears(7));
    	emp2 = new Empleado("12345678B", "Mario", Categoria.VENDEDOR, LocalDate.now().minusYears(15));
    	emp3 = new Empleado("12345678C", "Laura", Categoria.AUXILIAR, LocalDate.now().plusDays(1));
    } 

    //Casos de prueba constructor
    
    @Test
    public void testConstructor() {
        // Utilizar empleados estáticos
        assertEquals("12345678A", emp1.getDNI());
        assertEquals("Javier", emp1.getNombre());
        assertEquals(Categoria.ENCARGADO, emp1.getCategoria());
        assertEquals(LocalDate.now().minusYears(7), emp1.getFechaContratacion());
        assertFalse(emp1.getBaja());
    }
    
    @Test
    public void testConstructor_DNINulo() {
    	assertThrows(IllegalArgumentException.class, () -> {
    		new Empleado(null, "Javier", Categoria.ENCARGADO, LocalDate.now().minusYears(7));
    	});
    }
    
    @Test
    public void testConstructor_DNIFormatoIncorrecto() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Empleado("1234A", "Javier", Categoria.ENCARGADO, LocalDate.now().minusYears(7));
        });
    }
    
    @Test
    public void testConstructor_DNIVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Empleado("", "Javier", Categoria.ENCARGADO, LocalDate.now().minusYears(7));
        });
    }

    @Test 
    public void testConstructor_NombreNulo() {
    	assertThrows(IllegalArgumentException.class, () -> {
    		new Empleado("12345678A", null, Categoria.ENCARGADO, LocalDate.now().minusYears(7));
    	});
    }
    
    @Test
    public void testConstructor_NombreVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Empleado("12345678A", "", Categoria.ENCARGADO, LocalDate.now().minusYears(7));
        });
    }

    @Test 
    public void testConstructor_CategoriaNula() {
    	assertThrows(IllegalArgumentException.class, () -> {
    		new Empleado("12345678A", "Javier", null, LocalDate.now().minusYears(7));
    	});
    }

    @Test
    public void testConstructor_FechaContratacionNula() {
    	assertThrows(IllegalArgumentException.class, () -> {
    		new Empleado("12345678A", "Javier", Categoria.ENCARGADO, null);
    	});
    }
    
    @Test
    public void testConstructor_FechaContratacionFormatoIncorrecto() {
        assertThrows(DateTimeException.class, () -> {
            new Empleado("12345678A", "Javier", Categoria.ENCARGADO, LocalDate.of(2003, 15, 17));
        });
    }
    
    //Casos de prueba darDeAlta() y darDeBaja()

    @Test
    public void testDarDeBajaYAlta() {
        // Utilizar empleados estáticos
        assertFalse(emp1.getBaja());
        emp1.darDeBaja();
        assertTrue(emp1.getBaja());
        emp1.darDeAlta();
        assertFalse(emp1.getBaja());
    }
    
    @Test
    void testDarDeBaja_EmpleadoDeBaja() {
        // Utilizar empleados estáticos
        emp3.darDeBaja();

        // Intentamos dar de baja a un empleado que ya está de baja
        assertThrows(OperacionNoValidaException.class, () -> {
            try {
                emp3.darDeBaja();
            } catch (IllegalStateException e) {
                throw new OperacionNoValidaException("Este empleado ya está de baja");
            }
        });
    }

    @Test
    void testDarDeAlta_EmpleadoDeAlta() {
        // Utilizar empleados estáticos
        Empleado empleado = emp2;

        // Intentamos dar de alta a un empleado activo
        assertThrows(OperacionNoValidaException.class, () -> {
            try {
                empleado.darDeAlta();
            } catch (IllegalStateException e) {
                throw new OperacionNoValidaException("Este empleado ya está de alta");
            }
        });
    }
    
    //Casos de prueba validos sueldoBruto()
    
    @Test
    public void testSueldoBruto() {
        // Utilizar empleados estáticos
        assertEquals(2050.0, emp1.sueldoBruto(), 0.01);
        assertEquals(1600.0, emp2.sueldoBruto(), 0.01);
        assertEquals(1000.0, emp3.sueldoBruto(), 0.01);
    }
    
    //------------------------------------------------------------
    
    @Test
    public void testSueldoBruto_Hoy() {
        emp1.setFechaContratacion(LocalDate.now());
        assertEquals(2000.0, emp1.sueldoBruto(), 0.01);
        
        emp1.setFechaContratacion(LocalDate.now().minusYears(7));
    }

    @Test
    public void testSueldoBruto_HoyMenos2Anios() {
        emp2.setFechaContratacion(LocalDate.now().minusYears(2));
        assertEquals(1500.0, emp2.sueldoBruto(), 0.01);
        
        emp2.setFechaContratacion(LocalDate.now().minusYears(15));
    }

    @Test
    public void testSueldoBruto_HoyMenos5Anios() {
        emp3.setFechaContratacion(LocalDate.now().minusYears(5));
        assertEquals(1000.0, emp3.sueldoBruto(), 0.01);
        
        emp3.setFechaContratacion(LocalDate.now().plusDays(1));
    }
    
    //------------------------------------------------------------

    @Test
    public void testSueldoBruto_HoyMenos5AniosY1Dia() {
    	emp1.setFechaContratacion(LocalDate.now().minusYears(6));
        assertEquals(2050.0, emp1.sueldoBruto(), 0.01);
        
        emp1.setFechaContratacion(LocalDate.now().minusYears(7));
    }

    @Test
    public void testSueldoBruto_HoyMenos8Anios() {
    	emp2.setFechaContratacion(LocalDate.now().minusYears(8));
        assertEquals(1550.0, emp2.sueldoBruto(), 0.01);
        
        emp2.setFechaContratacion(LocalDate.now().minusYears(15));
    }

    @Test
    public void testSueldoBruto_HoyMenos10Anios() {
    	emp3.setFechaContratacion(LocalDate.now().minusYears(10));
        assertEquals(1050.0, emp3.sueldoBruto(), 0.01);
        
        emp3.setFechaContratacion(LocalDate.now().plusDays(1));
    }

    //--------------------------------------------------------------
    
    @Test
    public void testSueldoBruto_HoyMenos10AniosY1Dia() {
    	emp1.setFechaContratacion(LocalDate.now().minusYears(11));
        assertEquals(2100.0, emp1.sueldoBruto(), 0.01);
        
        emp1.setFechaContratacion(LocalDate.now().minusYears(7));
    }
    
    @Test
    public void testSueldoBruto_HoyMenos15Anios() {
    	emp2.setFechaContratacion(LocalDate.now().minusYears(15));
        assertEquals(1600.0, emp2.sueldoBruto(), 0.01);
        
        emp2.setFechaContratacion(LocalDate.now().minusYears(15));
    }
    
    @Test
    public void testSueldoBruto_HoyMenos20Anios() {
    	emp3.setFechaContratacion(LocalDate.now().minusYears(20));
        assertEquals(1100.0, emp3.sueldoBruto(), 0.01);
        
        emp3.setFechaContratacion(LocalDate.now().plusDays(1));
    }
    
    //-----------------------------------------------------------------

    @Test
    public void testSueldoBruto_HoyMenos20AniosY1Dia() {
    	emp1.setFechaContratacion(LocalDate.now().minusYears(21));
        assertEquals(2200.0, emp1.sueldoBruto(), 0.01);
        
        emp1.setFechaContratacion(LocalDate.now().minusYears(7));
    }
    
    @Test
    public void testSueldoBruto_HoyMenos40Anios() {
    	emp2.setFechaContratacion(LocalDate.now().minusYears(40));
        assertEquals(1700.0, emp2.sueldoBruto(), 0.01);
        
        emp2.setFechaContratacion(LocalDate.now().minusYears(15));
    }
    
    //Casos de prueba no validos sueldoBruto()
    
    @Test
    void testSueldoBruto_AntiguedadNegativa() {
        // Utilizar empleados estáticos
        Empleado empleado = emp3;

        // Intentamos calcular el sueldo con una antigüedad negativa
        assertThrows(OperacionNoValidaException.class, () -> {
            try {
                empleado.sueldoBruto(); // No se pasan argumentos
            } catch (IllegalArgumentException e) {
                throw new OperacionNoValidaException("Operacion invalida");
            }
        });
    }

    // Casos de prueba auxiliares para comprobar el correcto funcionamiento 
    // de los metodos sets y completar la cobertura

    @Test
    public void testSetDNI() {
        Empleado empleado = new Empleado();
        String nuevoDNI = "12345678D";
        empleado.setDNI(nuevoDNI);
        assertEquals(nuevoDNI, empleado.getDNI());
    }

    @Test
    public void testSetNombre() {
        Empleado empleado = new Empleado();
        String nuevoNombre = "nuevoNombre";
        empleado.setNombre(nuevoNombre);
        assertEquals(nuevoNombre, empleado.getNombre());
    }

    @Test
    public void testSetFechaContratacion() {
        Empleado empleado = new Empleado();
        LocalDate nuevaFechaContratacion = LocalDate.of(2025, 1, 1);
        empleado.setFechaContratacion(nuevaFechaContratacion);
        assertEquals(nuevaFechaContratacion, empleado.getFechaContratacion());
    }

    @Test
    public void testSetBaja() {
        Empleado empleado = new Empleado();
        empleado.setBaja(true);
        assertTrue(empleado.getBaja());
    }
 
    @Test
    public void testSetCategoria() {
        Empleado empleado = new Empleado();
        Categoria nuevaCategoria = Categoria.AUXILIAR;
        empleado.setCategoria(nuevaCategoria);
        assertEquals(nuevaCategoria, empleado.getCategoria());
    }
    
}

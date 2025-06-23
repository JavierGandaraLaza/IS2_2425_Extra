package es.unican.is2;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class VendedorEnPlantillaTest {
	
	private static VendedorEnPlantilla sutJunior;
	private static VendedorEnPlantilla sutSenior;

	
	@BeforeEach
	public void setUp(){
		sutJunior = new VendedorEnPlantillaJunior("Ana", "1", "11111111A");
		sutSenior = new VendedorEnPlantillaSenior("Pepe", "2", "222222222A");
	}
	
	@Test
	public void testConstructor() {
		assertEquals("1", sutJunior.getId());
		assertEquals("11111111A", sutJunior.getDni());
		assertEquals("Ana", sutJunior.getNombre());
		assertEquals(0.0, sutJunior.getTotalVentas());
		assertEquals(0.0, sutJunior.getComision());
		assertTrue(sutJunior instanceof VendedorEnPlantillaJunior);
		assertTrue(sutSenior instanceof VendedorEnPlantillaSenior);
		
	}

	@Test
	public void testAnhadeVenta() {
	
		sutJunior.anhade(200);
		assertEquals(200, sutJunior.getTotalVentas(), 0);
		sutJunior.anhade(300);
		assertEquals(500, sutJunior.getTotalVentas(), 0);
		sutJunior.anhade(0);
		assertEquals(500, sutJunior.getTotalVentas(), 0);
		
		sutSenior.anhade(300);
		assertEquals(300, sutSenior.getTotalVentas(), 0);
		sutSenior.anhade(300);
		assertEquals(600, sutSenior.getTotalVentas(), 0);
		sutSenior.anhade(0);
		assertEquals(600, sutSenior.getTotalVentas(), 0);
		
	}
	
	@Test
	public void testSetTotalVentas() {
		
		sutJunior.setTotalVentas(2000);
		assertEquals(2000, sutJunior.getTotalVentas(), 0);	
		sutJunior.setTotalVentas(4000);
		assertEquals(4000, sutJunior.getTotalVentas(), 0);	
		sutJunior.setTotalVentas(0);
		assertEquals(0, sutJunior.getTotalVentas(), 0);
		
		sutSenior.setTotalVentas(4500);
		assertEquals(4500, sutSenior.getTotalVentas(), 0);		
		sutSenior.setTotalVentas(4000);
		assertEquals(4000, sutSenior.getTotalVentas(), 0);
		sutJunior.setTotalVentas(0);
		assertEquals(0, sutJunior.getTotalVentas(), 0);	
		
	}
	
	@Test
	public void testSetComision() {
		
		sutJunior.setComision(2000);
		assertEquals(2000, sutJunior.getComision(), 0);	
		sutJunior.setComision(4000);
		assertEquals(4000, sutJunior.getComision(), 0);	
		sutJunior.setComision(0);
		assertEquals(0, sutJunior.getComision(), 0);
		
		sutSenior.setComision(4500);
		assertEquals(4500, sutSenior.getComision(), 0);		
		sutSenior.setComision(4000);
		assertEquals(4000, sutSenior.getComision(), 0);
		sutJunior.setComision(0);
		assertEquals(0, sutJunior.getComision(), 0);	
		
	}

	
	@Test
	public void testEquals() {
		VendedorEnPlantillaJunior igualJunior = new VendedorEnPlantillaJunior("Ana", "1", "11111111A");
		VendedorEnPlantillaJunior distintoIdJunior = new VendedorEnPlantillaJunior("Ana", "2", "11111111A");
		VendedorEnPlantillaJunior distintoDNIJunior = new VendedorEnPlantillaJunior("Ana", "1", "222222222A");
		
		assertEquals(igualJunior, sutJunior);
		assertNotEquals(distintoIdJunior, sutJunior);
		assertNotEquals(distintoDNIJunior, sutJunior);
		
		
		VendedorEnPlantillaSenior igualSenior = new VendedorEnPlantillaSenior("Pepe", "2", "222222222A");
		VendedorEnPlantillaSenior distintoIdSenior = new VendedorEnPlantillaSenior("Pepe", "3", "222222222A");
		VendedorEnPlantillaSenior distintoDNISenior = new VendedorEnPlantillaSenior("Pepe", "2", "33333333A");
		
		assertEquals(igualSenior, sutSenior);
		assertNotEquals(distintoIdSenior, sutSenior);
		assertNotEquals(distintoDNISenior, sutSenior);
		
		assertNotEquals(new Object(), sutSenior);
	}
	
	
	
}

package es.unican.is2;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
		assertEquals(sutJunior.getId(), "1");
		assertEquals(sutJunior.getDni(), "11111111A");
		assertEquals(sutJunior.getNombre(), "Ana");
		assertTrue(sutJunior.getTotalVentas()==0.0);
		assertTrue(sutJunior.getComision()==0.0);
		assertTrue(sutJunior instanceof VendedorEnPlantillaJunior);
		assertTrue(sutSenior instanceof VendedorEnPlantillaSenior);
		
	}

	@Test
	public void testAnhadeVenta() {
	
		sutJunior.anhade(200);
		assertEquals(sutJunior.getTotalVentas(), 200, 0);
		sutJunior.anhade(300);
		assertEquals(sutJunior.getTotalVentas(), 500, 0);
		sutJunior.anhade(0);
		assertEquals(sutJunior.getTotalVentas(), 500, 0);
		
		sutSenior.anhade(300);
		assertEquals(sutSenior.getTotalVentas(), 300, 0);
		sutSenior.anhade(300);
		assertEquals(sutSenior.getTotalVentas(), 600, 0);
		sutSenior.anhade(0);
		assertEquals(sutSenior.getTotalVentas(), 600, 0);
		
	}
	
	@Test
	public void testSetTotalVentas() {
		
		sutJunior.setTotalVentas(2000);
		assertEquals(sutJunior.getTotalVentas(), 2000, 0);	
		sutJunior.setTotalVentas(4000);
		assertEquals(sutJunior.getTotalVentas(), 4000, 0);	
		sutJunior.setTotalVentas(0);
		assertEquals(sutJunior.getTotalVentas(), 0, 0);
		
		sutSenior.setTotalVentas(4500);
		assertEquals(sutSenior.getTotalVentas(), 4500, 0);		
		sutSenior.setTotalVentas(4000);
		assertEquals(sutSenior.getTotalVentas(), 4000, 0);
		sutJunior.setTotalVentas(0);
		assertEquals(sutJunior.getTotalVentas(), 0, 0);	
		
	}
	
	@Test
	public void testSetComision() {
		
		sutJunior.setComision(2000);
		assertEquals(sutJunior.getComision(), 2000, 0);	
		sutJunior.setComision(4000);
		assertEquals(sutJunior.getComision(), 4000, 0);	
		sutJunior.setComision(0);
		assertEquals(sutJunior.getComision(), 0, 0);
		
		sutSenior.setComision(4500);
		assertEquals(sutSenior.getComision(), 4500, 0);		
		sutSenior.setComision(4000);
		assertEquals(sutSenior.getComision(), 4000, 0);
		sutJunior.setComision(0);
		assertEquals(sutJunior.getComision(), 0, 0);	
		
	}

	
	@Test
	public void testEquals() {
		VendedorEnPlantillaJunior igualJunior = new VendedorEnPlantillaJunior("Ana", "1", "11111111A");
		VendedorEnPlantillaJunior distintoIdJunior = new VendedorEnPlantillaJunior("Ana", "2", "11111111A");
		VendedorEnPlantillaJunior distintoDNIJunior = new VendedorEnPlantillaJunior("Ana", "1", "222222222A");
		
		assertTrue(sutJunior.equals(igualJunior));
		assertFalse(sutJunior.equals(distintoIdJunior));
		assertFalse(sutJunior.equals(distintoDNIJunior));
		
		
		VendedorEnPlantillaSenior igualSenior = new VendedorEnPlantillaSenior("Pepe", "2", "222222222A");
		VendedorEnPlantillaSenior distintoIdSenior = new VendedorEnPlantillaSenior("Pepe", "3", "222222222A");
		VendedorEnPlantillaSenior distintoDNISenior = new VendedorEnPlantillaSenior("Pepe", "2", "33333333A");
		
		assertTrue(sutSenior.equals(igualSenior));
		assertFalse(sutSenior.equals(distintoIdSenior));
		assertFalse(sutSenior.equals(distintoDNISenior));
		
		assertFalse(sutSenior.equals(new Object()));
	}
	
	
	
}

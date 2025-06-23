package es.unican.is2;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class VendedorEnPracticasTest {
	
	private static vendedorEnPracticas sut;

	@BeforeEach
	public void setUp(){
		sut = new vendedorEnPracticas("Ana", "1", "11111111A");
	}
	
	@Test
	public void testConstructor() {
		assertEquals("1", sut.getId());
		assertEquals("Ana", sut.getNombre());
		assertEquals("11111111A", sut.getDni());
		assertEquals(0.0, sut.getTotalVentas());
		assertEquals(0.0, sut.getComision());
	}
	
	@Test
	public void testSetT() {
		sut.setTotalVentas(100);
		assertEquals(100.0, sut.getTotalVentas());
		
		sut.setTotalVentas(230);
		assertEquals(230.0, sut.getTotalVentas());
		
		sut.setTotalVentas(0);
		assertEquals(0.0, sut.getTotalVentas());
	}
	
	@Test
	public void testSetC() {
		sut.setComision(100);
		assertEquals(100.0, sut.getComision());
		
		sut.setComision(230);
		assertEquals(230.0, sut.getComision());
		
		sut.setComision(0);
		assertEquals(0.0, sut.getComision());
	}

	@Test
	public void testAnhadeVenta() {
		sut.anhade(200);
		assertEquals(200.0, sut.getTotalVentas());
		
		sut.anhade(300);
		assertEquals(500.0, sut.getTotalVentas());	
		
		sut.anhade(0);
		assertEquals(500.0, sut.getTotalVentas());
		
	}
	
	@Test
	public void testEquals() {
		vendedorEnPracticas igual = new vendedorEnPracticas("Ana", "1", "11111111A");
		vendedorEnPracticas distintoId = new vendedorEnPracticas("Ana", "2", "11111111A");
		vendedorEnPracticas distintoNombre = new vendedorEnPracticas("Pepe", "1", "222222222A");
		
		assertEquals(igual, sut);
		assertNotEquals(distintoId, sut);
		assertNotEquals(distintoNombre, sut);
		
		assertNotEquals(new Object(), sut);
	}
	
	
	
}

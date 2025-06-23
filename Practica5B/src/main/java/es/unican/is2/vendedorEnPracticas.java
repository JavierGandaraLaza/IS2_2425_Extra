package es.unican.is2;

public class vendedorEnPracticas extends Vendedor {
	
	/**
	 * Retorna un nuevo vendedor en practicas
	 * @param nombre
	 * @param id
	 * @param dni
	 */
	public vendedorEnPracticas(String nombre, String id, String dni) { //WMC +1, CCog +0
		super(nombre, id, dni);
	}

	@Override
	public boolean equals(Object obj) { //WMC +1
		if (!(obj instanceof vendedorEnPracticas)) //WMC +1, CCog +1
			return false;
		vendedorEnPracticas v = (vendedorEnPracticas) obj;
		return (v.getId().equals(getId()) && v.getDni().equals(getDni())); //WMC +2, CCog +1
	}
}

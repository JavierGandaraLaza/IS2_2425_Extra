package es.unican.is2;

public class VendedorEnPlantilla extends Vendedor {
	
	/**
	 * Retorna un nuevo vendedor en plantilla del tipo que se indica
	 * @param nombre
	 * @param id
	 * @param dni
	 */
	public VendedorEnPlantilla(String nombre, String id, String dni) { //WMC +1, CCog +0
		super(nombre, id, dni);
	}

	@Override
	public void anhadeComision(double importe) { //WMC +1, CCog +0
	}
	
	@Override
	public boolean equals(Object obj) { //WMC +1 
		if (!(obj instanceof VendedorEnPlantilla)) //WMC +1, CCog +1
			return false;
		VendedorEnPlantilla v = (VendedorEnPlantilla) obj;
		return (v.getId().equals(getId()) && v.getDni().equals(getDni())); //WMC +2, CCog +1
	}
}

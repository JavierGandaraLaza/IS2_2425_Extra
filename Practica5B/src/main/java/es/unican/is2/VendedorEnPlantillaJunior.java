package es.unican.is2;

public class VendedorEnPlantillaJunior extends VendedorEnPlantilla {
	
	static final double COMISION_JUNIOR = 0.005;

	public VendedorEnPlantillaJunior(String nombre, String id, String dni) { //WMC +1, CCog +0
		super(nombre, id, dni);
	}

	 @Override
	 public void anhadeComision(double importe) { //WMC +1, CCog +0
	    double comision = importe * COMISION_JUNIOR;
	    this.anhade(importe);
	    this.setComision(this.getComision() + comision);
	 }

}

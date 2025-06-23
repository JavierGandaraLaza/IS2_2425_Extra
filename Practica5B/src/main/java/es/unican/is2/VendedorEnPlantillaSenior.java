package es.unican.is2;

public class VendedorEnPlantillaSenior extends VendedorEnPlantilla {
	
	static final double COMISION_SENIOR = 0.01;

	public VendedorEnPlantillaSenior(String nombre, String id, String dni) { //WMC +1, CCog +0
		super(nombre, id, dni);
	}

	 @Override
	 public void anhadeComision(double importe) { //WMC +1, CCog +0
	    double comision = importe * COMISION_SENIOR;
	    this.anhade(importe);
	    this.setComision(this.getComision() + comision);
	 }

}

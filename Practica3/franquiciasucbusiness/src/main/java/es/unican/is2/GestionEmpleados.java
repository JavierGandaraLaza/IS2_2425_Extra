package es.unican.is2;

public class GestionEmpleados implements IGestionEmpleados, IGestionAltasBajas {
    private ITiendasDAO t;
    private IEmpleadosDAO e;

    public GestionEmpleados(ITiendasDAO tiendasDAO, IEmpleadosDAO empleadosDAO) {
        this.t = tiendasDAO;
        this.e = empleadosDAO;
    }

    @Override
    public boolean bajaMedica(String dni) throws DataAccessException {
    	Empleado emp = e.empleado(dni);
        if (emp == null) {
            throw new DataAccessException();
        }
        if (emp.getBaja()) {
            return false; // Ya estaba de baja
        }
        emp.setBaja(true);
        e.modificarEmpleado(emp);
        return true;
    }

    @Override
    public boolean altaMedica(String dni) throws DataAccessException {
    	Empleado emp = e.empleado(dni);
        if (emp == null) {
            throw new DataAccessException();
        }
        if (!emp.getBaja()) {
            return false; // No estaba de baja
        }
        emp.setBaja(false);
        e.modificarEmpleado(emp);
        return true;
    }

    @Override
    public Empleado nuevoEmpleado(Empleado emp, String tienda) throws OperacionNoValidaException, DataAccessException {
    	// Verificar si la tienda existe
        Tienda tiendaa = t.tiendaPorNombre(tienda);
        if (tiendaa == null) {
            return null;
        }
        
        // Verificar si el empleado ya existe
        Empleado existente = e.empleado(emp.getDNI());
        if (existente != null) {
            throw new OperacionNoValidaException("El empleado ya existe");
        }
        
        // Asignar el empleado a la tienda
        tiendaa.getEmpleados().add(emp);
        
        // Crear el empleado
        return e.crearEmpleado(emp);
    }

    @Override
    public Empleado eliminarEmpleado(String dni, String tienda) throws OperacionNoValidaException, DataAccessException {
    	// Verificar si la tienda existe
        Tienda tiendaa = t.tiendaPorNombre(tienda);
        if (tiendaa == null) {
            return null;
        }
        
        // Obtener el empleado
        Empleado emp = e.empleado(dni);
        if (emp == null) {
        	return null;
        }
        
        // Verificar que el empleado pertenece a la tienda
        if (tiendaa.buscaEmpleado(dni).equals(null)) {
        	throw new OperacionNoValidaException("El empleado no pertenece a esta tienda");
        }
        
        // Eliminar el empleado de la tienda
        tiendaa.getEmpleados().remove(emp);
        
        // Eliminar el empleado
        return e.eliminarEmpleado(dni);
    }

    @Override
    public boolean trasladarEmpleado(String dni, String actual, String destino)
            throws OperacionNoValidaException, DataAccessException {
    	// Verificar tiendas
        Tienda tActual = t.tiendaPorNombre(actual);
        Tienda tDestino = t.tiendaPorNombre(destino);
        // Obtener empleado
        Empleado emp = e.empleado(dni);
        if (tActual == null || tDestino == null || tActual.equals(tDestino) || emp == null ) {
            return false;
        }
        
        // Verificar que el empleado pertenece a la tienda actual
        if (tActual.buscaEmpleado(dni).equals(null)) {
        	throw new OperacionNoValidaException("El empleado no pertenece a la tienda actual");
        }
        
        // Realizar el traslado
        tActual.getEmpleados().remove(emp);
        tDestino.getEmpleados().add(emp);
        
        return true;
    }

    @Override
    public Empleado empleado(String dni) throws DataAccessException {
    	Empleado emp = e.empleado(dni);
        if (emp == null) {
            throw new DataAccessException();
        }
        return emp;
    }

}

package es.unican.is2;

import java.util.List;

public class GestionTiendas implements IGestionTiendas {
    private ITiendasDAO tiendasDAO;

    public GestionTiendas(ITiendasDAO tiendasDAO) {
        this.tiendasDAO = tiendasDAO;
    }

    @Override
    public Tienda nuevaTienda(Tienda t) throws DataAccessException {
    	// Verificar si ya existe una tienda con el mismo nombre
        Tienda existente = tiendasDAO.tienda(t.getId());
        if (existente != null) {
            return null;
        }
        
        // Crear la nueva tienda
        return tiendasDAO.crearTienda(t);
    }

    @Override
    public Tienda eliminarTienda(String nombre) throws OperacionNoValidaException, DataAccessException {
    	// Obtener la tienda
        Tienda t = tiendasDAO.tiendaPorNombre(nombre);
        if (t == null) {
            return null;
        }
        
        // Verificar si tiene empleados
        if (!t.getEmpleados().isEmpty()) {
            throw new OperacionNoValidaException("No se puede eliminar una tienda con empleados");
        }
        
        // Eliminar la tienda
        return tiendasDAO.eliminarTienda(t.getId());
    }
    
    
    @Override
    public List<Tienda> tiendas() throws DataAccessException {
    	return tiendasDAO.tiendas();
    }
    

    @Override
    public Tienda tienda(String nombre) throws DataAccessException {
    	Tienda t = tiendasDAO.tiendaPorNombre(nombre);
        if (t == null) {
            return null;
        }
        return t;
    }
    
}

package edu.uade.tp_db.servicios;

import edu.uade.tp_db.entidades.Producto;
import edu.uade.tp_db.repositorios.ProductoRepositorio;
import edu.uade.tp_db.servicios.interfaces.IProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosService implements IProductosService {

    @Autowired
    ProductoRepositorio productoRepository;

    @Override
    public List<Producto> verProductos() {
        return productoRepository.findAll();
    }
}

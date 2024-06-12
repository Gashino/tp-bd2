package edu.uade.tp_db.servicios;

import edu.uade.tp_db.entidades.Carrito;
import edu.uade.tp_db.entidades.Item;
import edu.uade.tp_db.repositorios.CarritoRepositorio;
import edu.uade.tp_db.servicios.interfaces.ICarritosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarritoService implements ICarritosService {

    @Autowired
    CarritoRepositorio carritoRepositorio;

    @Override
    public void agregarProductoACarrito(Item item, Carrito carrito) {
        //mongodb actualizacion de carrito
        carrito.addItem(item);
        carritoRepositorio.save(carrito);
    }

    @Override
    public void quitarProductoDeCarrito(Item item, Carrito carrito) {
        //mongodb actualizacion de carrito
        carrito.removeItem(item);
        carritoRepositorio.save(carrito);
    }

    @Override
    public Carrito getCarritoPorUsuario(String idUsuario) {
        Optional<Carrito> carrito = carritoRepositorio.findByUsuario(idUsuario);
        if(carrito.isPresent()){
            return carrito.get();
        }
        else {return new Carrito(idUsuario);
        }

    }
}

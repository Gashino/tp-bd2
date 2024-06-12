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
    @Autowired
    RedisService redisService;

    @Override
    public void agregarProductoACarrito(Item item, Carrito carrito) {
        //mongodb actualizacion de carrito
        carrito.addItem(item);
        carritoRepositorio.save(carrito);

        //Redis actualizacion de carrito en pila
        redisSaveCarrito(carrito);
    }

    @Override
    public void quitarProductoDeCarrito(Item item, Carrito carrito) {
        //mongodb actualizacion de carrito
        carrito.removeItem(item);
        carritoRepositorio.save(carrito);

        //Redis actualizacion de carrito en pila
        redisSaveCarrito(carrito);
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

    @Override
    public void redisSaveCarrito(Carrito carrito) {
        redisService.redisSaveCarrito(carrito);
    }

    @Override
    public void redisDesapilarCarrito(Carrito carrito) {
        redisService.redisDesapilarCarrito(carrito);
    }

    public void getAnteriorCarrito(Carrito carrito) {

        //Redis get anterior carrito
        redisDesapilarCarrito(carrito);
        //mongodb actualizacion de carrito
        carritoRepositorio.save(carrito);
    }

    public void eliminarCarrito(Carrito carrito) {
        carritoRepositorio.delete(carrito);
    }
}

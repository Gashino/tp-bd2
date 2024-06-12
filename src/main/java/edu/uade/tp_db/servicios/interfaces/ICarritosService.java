package edu.uade.tp_db.servicios.interfaces;

import edu.uade.tp_db.entidades.Carrito;
import edu.uade.tp_db.entidades.Item;

public interface ICarritosService {
    public void agregarProductoACarrito(Item item, Carrito carrito);
    public void quitarProductoDeCarrito(Item item, Carrito carrito);
    public Carrito getCarritoPorUsuario(String idUsuario);
}

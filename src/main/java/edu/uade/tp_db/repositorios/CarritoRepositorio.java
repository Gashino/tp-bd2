package edu.uade.tp_db.repositorios;


import edu.uade.tp_db.entidades.Carrito;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CarritoRepositorio extends MongoRepository<Carrito, String> {
    public Optional<Carrito> findByUsuario(String idUsuario);
}

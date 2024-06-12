package edu.uade.tp_db.repositorios;

import edu.uade.tp_db.entidades.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductoRepositorio extends MongoRepository<Producto, String>{
}

package edu.uade.tp_db.repositorios;
import edu.uade.tp_db.entidades.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuariosRepositorio extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByCorreo(String email);
}

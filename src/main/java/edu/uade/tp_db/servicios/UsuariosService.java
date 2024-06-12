package edu.uade.tp_db.servicios;


import edu.uade.tp_db.entidades.Usuario;
import edu.uade.tp_db.repositorios.UsuariosRepositorio;
import edu.uade.tp_db.servicios.interfaces.IUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UsuariosService implements IUsuariosService {

    @Autowired
    UsuariosRepositorio userRepository;

    public Usuario agregarUsuario(Usuario usuario) {
        return userRepository.save(usuario);
    }

    @Override
    public Usuario logearUsuario(String email, String password) {
        Optional<Usuario> user = userRepository.findByCorreo(email);
        if(user.isPresent() && user.get().getPassword().equals(password)){
            return user.get();

        }else{return null;}
    }

    public boolean existeUsuario(String email){
        Optional<Usuario> user = userRepository.findByCorreo(email);
        if(user.isPresent()){
            return true;
        }else{
            return false;
        }
    }

    public void actualizarUsuario(Usuario usuario){
        userRepository.save(usuario);
    }
}

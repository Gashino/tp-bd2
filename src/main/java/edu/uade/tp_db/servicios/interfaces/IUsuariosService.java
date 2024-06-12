package edu.uade.tp_db.servicios.interfaces;

import edu.uade.tp_db.entidades.Usuario;

public interface IUsuariosService {
    public Usuario agregarUsuario(Usuario usuario);
    public Usuario logearUsuario(String email,String password);
}

package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;

import java.util.List;

//en una interface yo le digo que métodos o funciones puede tener una clase
//y si la clase implementa esta interface, va a estar obligada a utilizar esas funciones
public interface UsuarioDao {

    //le voy diciendo a que métodos va a tener esta interface que luego van a tener que ser implementados
    List<Usuario> getUsuarios();

    void eliminar(Long id);

    void registrar(Usuario usuario);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}

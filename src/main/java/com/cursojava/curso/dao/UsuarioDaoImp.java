package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//creo una clase que va a implementar la interfaz. Esto lo hago porque estoy usando un patrón de diseño. WTF
@Repository //hace referencia a que va a tener la funcionalidad de acceder al repositorio de la base de datos
@Transactional //hace referencia en que va a hacer las consultas en formato de transaccion wtf
public class UsuarioDaoImp implements UsuarioDao {

    @PersistenceContext //esto es para hacer referencia en algun contexto de la base de datos
    private EntityManager entityManager; //esto nos sirve para hacer la conexion con la base datos

    @Override
    public List<Usuario> getUsuarios() {
        //estamos trabajando con hibernate entonces las query son distintas
        String query = "FROM Usuario"; //usuario hace referencia a la clase

        return  entityManager.createQuery(query).getResultList();
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id); //busco el usuario en la bbdd del que estoy haciendo click
    entityManager.remove(usuario); //lo elimino
    }

    @Override
    public void registrar(Usuario usuario) {
        //lo guardo en la base de datos
        entityManager.merge(usuario); //el merge sirve para actualizar entidades y tambien crear, pero se usa mas para actualizar, me dio problemas de id, no lo pone automaticamente...
    }

    @Override
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        //estamos trabajando con hibernate entonces las query son distintas
        String query = "FROM Usuario WHERE email = :email "; //se pone :email para evitar inyeccion SQL jajaj porque si paso directamente los parametros pueden mandarme un codigo sql

        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();


        if (lista.isEmpty()){
            return null;
        }

        String passwordHashed = lista.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if(argon2.verify(passwordHashed, usuario.getPassword())){
            return lista.get(0); //si la credencial es correcta que me devuelva el usuario
        }
        return null;

    }
}

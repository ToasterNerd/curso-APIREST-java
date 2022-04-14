package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//este es un controlador que se va a encargar de controlar las interacciones con la base de datos.

//EN SPRING BOOT SE MANEJA todito CON ANOTACIONES. IMPORTANTE HACER ESTO SIEMPRE.
//hago un metodo para cada cosa, agregar usuario, traer, modificar, eliminar.
@RestController
public class UsuarioController {

    @Autowired
    //hace que la clase usuarioDaoImp cree un objeto y se guarde adentro de UsuarioDao. Hay que tener cuidado porque queda compartido en memoria
    private UsuarioDao usuarioDao; //esto es inyeccion de dependencias. cargo desde afuera al usuarioDao un tipo de objeto

    @Autowired
    private JWTUtil jwtUtil;

    //cargo un registro en la bbdd
    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.GET) //
    public Usuario getUsuario(@PathVariable Long id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Juan");
        usuario.setApellido("Rios");
        usuario.setEmail("jpr230697@gmail.com");
        usuario.setTelefono("3764178174");
        return usuario;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    //así tengo que poner para que el metodo por url reciba una usuario/{id}
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token) {//metodo de tipo lista //este es para traer todos los usuarios

        if (!validarToken(token)) {
            return null;
        }
        return usuarioDao.getUsuarios();//con esto, cuando entro a localhost/usuarios me va a mostrar información traida de la base de datos.

    }

    //creo una funcion para controlar la validación del token
    private boolean validarToken(String token) {
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null; //si el usuario no es nulo hago lo que sigue, pero si es nulo no retorno nada
    }


    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    //así tengo que poner para que el metodo por url reciba una usuario/{id}
    public void registrarUsuarios(@RequestBody Usuario usuario) {//con @requestbody convierte el json en un objeto tipo Usuario

        //paso a hash la contraseña
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);

        usuarioDao.registrar(usuario);//con esto, cuando entro a localhost/usuarios me va a mostrar información traida de la base de datos.

    }

    //hago que se elimine un registro de la bbdd
    //entro por esa url de abajo y mediante el metodo DELETE, el cual no quiere decir que se va a eliminar, pero se va a hacer la funcion de abajo
    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE) //
    public void eliminar(@RequestHeader(value = "Authorization") String token,
                         @PathVariable Long id) {
        if (!validarToken(token)) {
            return;
        }
        usuarioDao.eliminar(id);
    }

   /* @RequestMapping(value = "editar") //
    public Usuario editar(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setApellido("Rios");
        usuario.setEmail("jpr230697@gmail.com");
        usuario.setTelefono("3764178174");
        return usuario;
    }*/
}

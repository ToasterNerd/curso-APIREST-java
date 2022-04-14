package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired //con este ya aplico la inyeccion de dependencias al objeto jwtutil
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST) //así tengo que poner para que el metodo por url reciba una usuario/{id}
    public String login(@RequestBody Usuario usuario){//con @requestbody convierte el json en un objeto tipo Usuario

        Usuario usuarioLogueado= usuarioDao.obtenerUsuarioPorCredenciales(usuario);

        if(usuarioLogueado != null){

            //creamos aca el jwt
           String tokenJWT =  jwtUtil.create(String.valueOf(usuarioLogueado.getId()),usuarioLogueado.getEmail());

           //aca podemos hacer que retorne al cliente todito lo qu quiera para no hacer muchas llamadas al servidor.
            return tokenJWT;
        };
    return "FAIL";
    }
}

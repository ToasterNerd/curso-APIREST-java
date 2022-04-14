package com.cursojava.curso.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity//con esto hago referencia a que va a ser una entidad de base de datos????
@Table(name = "usuarios") //aca le indico a que tabla tiene que ir de la bdd cuando la llamo desde UsuarioDaoImp
@ToString @EqualsAndHashCode
public class Usuario { //ahora quiero hacer que mediante una url me devuelva un json de un usuario, entonces:

    @Id //le indico que esta va a ser la clave primaria.
    @GeneratedValue(strategy=GenerationType.IDENTITY) //hago que se genere automaticamente el id en la bdd (hibernate)
    @Getter @Setter @Column(name = "id") //con estos 2 ya estoy diciendo que se creen  los getters y setters de la variable de abajo y tambien defino columna para hibernate
    private Long id;
    @Getter @Setter @Column(name = "nombre")
    private String nombre;
    @Getter @Setter @Column(name = "apellido")
    private String apellido;
    @Getter @Setter @Column(name = "email")
    private String email;
    @Getter @Setter @Column(name = "telefono")
    private String telefono;
    @Getter @Setter @Column(name = "password")
    private String password;

    //usamos metodos getters y setters, aclaramos que esto es una convencion para trabajar, no es obligatorio
    //ahora existe otra convención que es mediante un método builder. ???¿¿¿

    //con click derecho y generate setters y getters se hace de una. PTM







}

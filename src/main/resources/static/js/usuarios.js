// Call the dataTables jQuery plugin
$(document).ready(function() { //esto es para que ejecute el codigo cuando este ready la pagina
    //entonces puedo meter un alert y me doy cuenta que a penas termina de cargar la pagina salta el alert
    //alert(32123123);

    cargarUsuarios();
  $('#usuarios').DataTable();//esto es un plugin que me convierte en una tabla con un monton de funcionalidades.

  actualizarEmailDelUsuario();
});

function actualizarEmailDelUsuario(){
    document.getElementById('txt-email-usuario').outerHTML = localStorage.email;
}

//cuando se llame a la pagina, se ejecuta cargaUsuarios
//esta funcion va a llamar a la funcion java usuarios y va a recibir un json con los usuarios
//y despues los va a mostrar en esa parte del html con la linea document.querySelector...

async function cargarUsuarios(){//tengo que decirle que es asincronica la funcion con el async
  const request = await fetch('api/usuarios', { //espero el resultado del llamado, pero si uso await, es async

    method: 'GET',
    headers: getHeaders()
  });
  const usuarios = await request.json(); //el resultado se convierte en json (los usuarios)


//armo una lista de los usuarios en formato html y despues los muestro en el html

let listadoHTML = "";
//a un usuario le cargo ese codigo html que es el row en formato table, y por cada usuario le cargo en la tabla con sus datos
    for (let usuario of usuarios){
    let botonEliminar = '<a href="#" onclick="eliminarUsuario('+usuario.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>'
    let telefonoTexto = usuario.telefono ==null ? '-' : usuario.telefono; //un if en una sola linea

    let usuarioHTML = '<tr><td>'+usuario.id+'</td><td>'+usuario.nombre+' ' +usuario.apellido+ '</td><td>'
    +usuario.email+'</td><td>'+telefonoTexto
    +'</td> <td>'+botonEliminar+ '</td></tr>';

listadoHTML += usuarioHTML;
}

  console.log(usuarios);

document.querySelector('#tableUsuarios tbody').outerHTML = listadoHTML;
}




//con esta funcion hago la prueba
async function  eliminarUsuario(id){ //esta funcion quiero que se llame cuando se hace click en el boton

    if (!confirm('¿desea eliminar este usuario?')){
    //si hace click en no hace lo de abajo
    return;//que salga de la funcion
    }

    const request = await fetch('api/usuarios/' + id, { //espero el resultado del llamado, pero si uso await, es async

     method: 'DELETE',
     headers:getHeaders()
     });
//alert(id)

location.reload()
}

function getHeaders(){ //hago una funcion para mandar el header
return{
       'Accept': 'application/json',
       'Content-Type': 'application/json',
       //pongo un header mas para eliminar el usuario mandando el tokenJWT
       'Authorization': localStorage.token
       };
}

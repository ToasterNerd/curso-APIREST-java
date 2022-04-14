$(document).ready(function() { //esto es para que ejecute el codigo cuando este ready la pagina
    //on ready
});

//cuando se llame a la pagina, se ejecuta cargaUsuarios
//esta funcion va a llamar a la funcion java usuarios y va a recibir un json con los usuarios
//y despues los va a mostrar en esa parte del html con la linea document.querySelector...

async function registrarUsuario(){//tengo que decirle que es asincronica la funcion con el async
  let datos = {}; //en datos tiene que estar literalmente lo que pone el usuario en el registro y enviarlo a la bbdd
  datos.nombre = document.getElementById('txtNombre').value;
  datos.apellido = document.getElementById('txtApellido').value;
  datos.email = document.getElementById('txtEmail').value;
  datos.password = document.getElementById('txtPassword').value;

  let repetirPassword = document.getElementById('txtRepetirPassword').value;

  if(repetirPassword != datos.password){
  alert("la contraseña que escribiste es diferente. ")
  return; //sale de la funcion
  }

  const request = await fetch('api/usuarios', { //espero el resultado del llamado, pero si uso await, es async

    method: 'POST', //USO POST PARA CREAR EL USUARIO
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos) //llama a la funcion stringify y convierte cualquier objeto javascript en json
  });

  alert("la cuenta fue creada con exito");
    window.location.href = "login.html"
}




$(document).ready(function() { //esto es para que ejecute el codigo cuando este ready la pagina
    //on ready
});

//cuando se llame a la pagina, se ejecuta cargaUsuarios
//esta funcion va a llamar a la funcion java usuarios y va a recibir un json con los usuarios
//y despues los va a mostrar en esa parte del html con la linea document.querySelector...

async function iniciarSesion(){//tengo que decirle que es asincronica la funcion con el async
  let datos = {}; //en datos tiene que estar literalmente lo que pone el usuario en el registro y enviarlo a la bbdd
  datos.email = document.getElementById('txtEmail').value;
  datos.password = document.getElementById('txtPassword').value;

  const request = await fetch('api/login', { //espero el resultado del llamado, pero si uso await, es async

    method: 'POST', //USO POST PARA CREAR EL USUARIO
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos) //llama a la funcion stringify y convierte cualquier objeto javascript en json
  });

  const respuesta = await request.text(); //me traigo info de la sesion
  if(respuesta != "FAIL"){
  localStorage.token = respuesta; //guardo el token que recibo en el lado del cliente o browser.
  localStorage.email = datos.email;
  window.location.href = "usuarios.html"
  }else{
  alert("las credenciales son incorrectas, por favor intente nuevamente");
  }

}



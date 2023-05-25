/**
 * Selección de boton para crear contacto
 */
const newContact = document.querySelector(".selection-btn");
/**
 * Selección del contenedor donde aparecera el formulario
 */

/**
 * Muestra el formulario cuando el usuario quiera crear un nuevo contacto
 */
newContact.addEventListener("click", function() {
    const container = document.querySelector(".contact-form");

    if(document.querySelector("form") === null) {

        container.innerHTML += `
    <form id='user-form'>
        <label for="nombre">Nombres <span>*</span></label>
        <input name="nombre" required type="text" id="name">

        <label for="apellido">Apellidos <span>*</span></label>
        <input name="apellido" required type="text" id="lastname">

        <label for="email">Correo electronico <span>*</span></label>
        <input name="email" required type="text" id="email">

        <label for="telefono">Número de teléfono <span>*</span></label>
        <input name="telefono" required type="text" id="phone">

        <label for="fechaDeNacimiento"> Fecha de nacimiento <span>*</span></label>
        <input name="fechaDeNacimiento" required type="date" format="DD-MM-YYYY" id="birth-date" >

        <span>
            <button type="submit" class="button-add" action>Agregar contacto nuevo</button>
        </span>

    </form>`;

    }

    const form = document.getElementById('user-form');

    form.addEventListener('submit', function(e) {
      console.log('formulario enviado');
      addContact(form);
    });

})

async function addContact(formData) {

  const info = new FormData(formData);
  const date = new Date(info.get('fechaDeNacimiento'));
  const birthDate = date.toLocaleDateString('es-Es', { day: '2-digit', month: '2-digit', year: 'numeric'}).replace(/-/g, '/');
    try {
      const data = await axios.post('http://localhost:8080/contact', {
        nombre: info.get('nombre'),
        apellido: info.get('apellido'),
        email: info.get('email'),
        telefono: info.get('telefono'),
        fechaDeNacimiento: birthDate
      },
      {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      });
      console.log(data);
      console.log("contacto agregado");
    }catch {
      console.log("Error al agregar contacto");
    }
  }

  /*async function addContact(form) {
    console.log(form)

    const {data} = await axios.post('http://localhost:8080/contact', form, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      })
    try {
      
    }catch {
      console.log("Error al agregar contacto");
    }
  }*/
  
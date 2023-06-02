/**
 * Función que accede a los contactos almacenados y los muestra en una tabla
 */
async function getContacts() {
    try {
      const response = await axios.get('http://localhost:8080/contacts');
      console.log('funciona');
      const contacts = response.data;
      const table = document.getElementById("contacts-table");
      for(let contact of contacts) {
        //Condición que verifica que el contacto no se encuentra borrado de forma lógica
        if(!contact.borrado) {
          const birthDate = new Date(contact.fechaDeNacimiento);
          const inputName = '<input required id="input-name'+contact.id+'" class="hide input-table" value='+contact.nombre+'>';
          const inputLastname = '<input required id="input-lastname'+contact.id+'" class="hide input-table" value='+contact.apellido+'>';
          const inputEmail = '<input required id="input-email'+contact.id+'" class="hide input-table" value='+contact.email+'>';
          const inputPhone = '<input required id="input-phone'+contact.id+'" class="hide input-table" value='+contact.telefono+'>';
          const inputBirthDate = '<input required type="date" id="input-birthdate'+contact.id+'" class="hide input-table" value='+birthDate.toISOString().split('T')[0]+'>';
          const container = '<div id="hola" class="info'+contact.id+'">';
          console.log(contact);

          table.innerHTML += `
          <tr> 
            <td>${contact.id}</td>
            <td>${inputName} ${container}${contact.nombre}</div></td>
            <td>${inputLastname} <div class="info${contact.id}">${contact.apellido}</div></td>
            <td>${inputEmail} <div class="info${contact.id}">${contact.email}</div></td>
            <td>${inputPhone} <div class="info${contact.id}">${contact.telefono}</div></td>
            <td>${inputBirthDate} <div class="info${contact.id}">${birthDate.toLocaleDateString('es-Es', 
            { day: '2-digit', month: '2-digit', year: 'numeric'})}</div></td>
            <td><button class="button-edit edit" index= ${contact.id}>editar</button>
            <button class="save-btn hide" id="save${contact.id}" index= ${contact.id}>Guardar</button></td>
            <td><button class="button-edit delete" value= ${contact.id}>eliminar</button></td>
          </tr>
          `;
        }
      }
      deleteContact();
      editContact();
    } catch (error) { 
      alert('No se logro acceder a la API de contactos.')
      console.error(error);
    }
}

/**
 * Método encargado de realizar el borrado lógico de los contactos
 */
function deleteContact() {
  //Variable que almacena todos los botones con la clase delete presentes en la pagina
  const deleteButtons = document.querySelectorAll('.delete');
  for(let button of deleteButtons) {
    button.addEventListener("click", async event => {
      const deletedConfirm = window.confirm('¿Está seguro que quiere eliminar el contacto?');
      if(deletedConfirm === true) {
        const id = event.target.getAttribute('value')
        const response = await axios.patch('http://localhost:8080/contact/Deleted/'+id,{
          borrado: true
        },
        {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        });
        alert('Contacto eliminado.')
        window.location.reload();
      }
    })
  }
} 

/**
 * Método que activa los campos para ingresar los datos que se quieren actualizar del contacto
 */
function editContact() {
  //Variable que almacena todos los botones con la clase edit presentes en la pagina
  const editButtons = document.querySelectorAll('.edit');
  for(let button of editButtons) {
    button.addEventListener("click", event => {
      const index = event.target.getAttribute('index');
      button.classList.add("hide");
      show(index);
    })
  }
}

/**
 * Método para mostrar boton guardar y los input de los datos
 * @param {*id del contacto} index 
 */
function show(index) {
  const divs = document.querySelectorAll('.info'+index);
  const saveButton = document.querySelector('#save'+index);
  console.log(divs);
  const name = document.querySelector('#input-name'+index);
  const lastname = document.querySelector('#input-lastname'+index);
  const email = document.querySelector('#input-email'+index);
  const phone = document.querySelector('#input-phone'+index);
  const date = document.querySelector('#input-birthdate'+index);
  console.log(name);

// Ciclo para mostrar las celdas de la tabla como entradas
  for(let div of divs) {
    div.classList.add("hide");
  }
  name.classList.remove("hide");
  lastname.classList.remove("hide");
  email.classList.remove("hide");
  phone.classList.remove("hide");
  date.classList.remove("hide");
  saveButton.classList.remove("hide");
  saveContact(index);
}

/**
 * Método para guardar la información actualizada del contacto
 * @param {*id del contacto} index 
 */
function saveContact(index) {
  const saveButtons = document.querySelectorAll(".save-btn");
  
  for(let button of saveButtons) {
    button.addEventListener("click", event => {
      const name = document.querySelector('#input-name'+index).value;
      const lastname = document.querySelector('#input-lastname'+index).value;
      const email = document.querySelector('#input-email'+index).value;
      const phone = document.querySelector('#input-phone'+index).value;
      const dateInput = document.querySelector('#input-birthdate'+index).value;
      const date = new Date(dateInput);
      console.log(name);
      try {
        axios.put('http://localhost:8080/contact/'+index,{
          nombre: name,
          apellido: lastname,
          email: email,
          telefono: phone,
          fechaDeNacimiento: date.toLocaleDateString('es-Es', { day: '2-digit', month: '2-digit', year: 'numeric'})
        }, {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        });
        window.alert('Contacto actualizado');
        window.location.reload();

      }catch {
        console.log('No se pudo actualizar la información del contacto');
        window.alert('No se pudo actualizar la información del contacto');
      }
    })
  }
}
getContacts();
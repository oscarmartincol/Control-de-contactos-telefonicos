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
        if(!contact.borrado) {
          const birthDate = new Date(contact.fechaDeNacimiento);

          table.innerHTML += `
          <tr> 
            <td>${contact.id}</td>
            <td>${contact.nombre}</td>
            <td>${contact.apellido}</td>
            <td>${contact.email}</td>
            <td>${contact.telefono}</td>
            <td>${birthDate.toLocaleDateString('es-Es', { day: '2-digit', month: '2-digit', year: 'numeric'})}</td>
            <td><button class="button-edit edit" value= ${contact.id}>editar</button></td>
            <td><button class="button-edit delete" value= ${contact.id}>eliminar</button></td>
          </tr>
          `;
        }
      }
      deleteContact();
    } catch (error) { 
      alert('No se logro acceder a la API de contactos.')
      console.error(error);
    }
}

/**
 * Método encargado de realizar el borrado lógico de los contactos
 */
function deleteContact() {
  //Variable que almacena todos los botones con la clase delete presentes en el archivo html
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
getContacts();
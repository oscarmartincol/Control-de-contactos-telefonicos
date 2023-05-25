//const addContact = document.querySelector(".button-add");


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
            <td><button class="button-edit">editar</button></td>
            <td><button class="button-edit">eliminar</button></td>
          </tr>
          `;
        }
      }
    } catch (error) { 
      console.log('error al llamar la API');
      console.error(error);
    }
}

getContacts();
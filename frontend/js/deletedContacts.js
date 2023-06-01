async function showDeletedContacts() {
    try {
        const response = await axios.get('http://localhost:8080/contacts');
        const contacts = response.data;

        const body = document.querySelector('#body');
        
        for(let contact of contacts) {
            const buttonDelete = '<button class="delete-btn" value='+contact.id+'>Eliminar</button>';
            const buttonRestore = '<button class="restore-btn" value='+contact.id+'>Restaurar</button>';
            const date = new Date(contact.fechaDeNacimiento);
            const birthDate = date.toLocaleDateString('es-Es', { day: '2-digit', month: '2-digit', year: 'numeric'}).replaceAll("/", "-");
            if(contact.borrado) {
                body.innerHTML += `<tr>
                <td>${contact.id}</td>
                <td>${contact.nombre}</td>
                <td>${contact.apellido}</td>
                <td>${contact.email}</td>
                <td>${contact.telefono}</td>
                <td>${birthDate}</td>
                <td>${buttonDelete}</td>
                <td>${buttonRestore}</td>
                </tr>`;
            }
        }
        deleteContacts();
        restoreContact();
    } catch (error){
        window.alert('Error al acceder a la Base de datos');
        console.log(error);
    }
}

function deleteContacts() {
    const deleteButtons = document.querySelectorAll('.delete-btn');
    for(let button of deleteButtons) {
        button.addEventListener("click", async event => {
            console.log('eliminar fue pulsado')
            const confirm = window.confirm('Esta acción es permanente, ¿Está seguro que quiere eliminar el contacto?');
            if(confirm === true) {
                const id = event.target.getAttribute('value');
                try {
                    const response = await axios.delete('http://localhost:8080/contact/'+id);
                    if(response.status === 200) {
                        window.location.reload();
                        window.alert('El contacto ha sido eliminado.');
                    }
                }catch(error) {
                    window.alert('Ocurrio un error al intentar borrar el contacto.')
                    console.log(error);
                }

            }
        })
    }

}

function restoreContact() {
    const restoreButtons = document.querySelectorAll('.restore-btn');
    for(let button of restoreButtons) {
        button.addEventListener("click", async event => {
            const id = event.target.getAttribute('value');

            try {
                const response = await axios.patch('http://localhost:8080/contact/Deleted/'+id,{
                borrado: false
                },
                {
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                });
                if(response.status === 200) {
                    window.alert('El contacto ha sido recuperado');
                    window.location.reload();
                }

            }catch(error) {
                window.alert('Error al restaurar el contacto');
                console.log(error);
            }

        });
    }
}

showDeletedContacts();
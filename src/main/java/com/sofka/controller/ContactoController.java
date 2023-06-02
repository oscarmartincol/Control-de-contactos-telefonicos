package com.sofka.controller;
import com.sofka.domain.Contacto;
import com.sofka.service.ContactoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

/**
 *Controlador de la API donde se realizan las peticiones SQL según la ruta.
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class ContactoController {

    /**
     * Instancia de la clase de servicio con los métodos necesarios para ejecutar sentencias SQL
     */
    @Autowired
    private ContactoService contactoService;

    /**
     * Método para obtener la lista de contactos almacenados en la base de datos.
     * @return Lista de contactos almacenados.
     */
    @GetMapping(path = "/contacts")
    public List<Contacto> listaDeContactos() {
        var contactos = contactoService.listar();
        return contactos;
    }

    /**
     * Método para guardar un nuevo contacto a la base de datos.
     * @param contacto datos del contacto que se quiere guardar.
     * @return Código del estado HTTP de la operación.
     */
    @PostMapping(path = "/contact")
    public ResponseEntity<Contacto> crearContacto(Contacto contacto) {
        log.info("Datos del contacto a crear: {}", contacto);
        contactoService.save(contacto);
        return new ResponseEntity<>(contacto, HttpStatus.CREATED);
    }

    /**
     * Método para borrar fisicamente un contacto de la base de datos según el id proporcionado
     * @param contacto contacto que se quiere borrar
     * @return Código del estado HTTP de la operación.
     */
    @DeleteMapping (path = "/contact/{id}")
    public ResponseEntity<Contacto> borrarContacto(Contacto contacto) {
        log.info("Contacto a borrar: {}", contacto);
        contactoService.delete(contacto);
        return new ResponseEntity<>(contacto, HttpStatus.OK);
    }

    /**
     * Método para actualizar la información de un contacto almacenado en la base de datos
     * @param contacto Datos nuevos del contacto
     * @param id identificación del contacto que se quiere actualizar.
     * @return Código del estado HTTP de la operación.
     */
    @PutMapping(path = "/contact/{id}")
    public ResponseEntity<Contacto> actualizarContacto(Contacto contacto, @PathVariable("id")Long id) {
        log.info("Contacto a modificar: {}", contacto);
        contactoService.update(id, contacto);
        return new ResponseEntity<>(contacto, HttpStatus.OK);
    }

    /**
     * Método para realizar el borrado lógico de un contacto
     * @param contacto Contacto que se quiere eliminar
     * @param id identificación del contacto en la base de datos
     * @return Código del estado HTTP de la operación.
     */
    @PatchMapping(path = "/contact/Deleted/{id}")
    public ResponseEntity<Contacto> borradoLogico(Contacto contacto, @PathVariable("id")Long id) {
        log.info("Contacto a borrar: {}", contacto);
        contactoService.updateBorradoLogico(id, contacto);
        return new ResponseEntity<>(contacto, HttpStatus.OK);
    }
}

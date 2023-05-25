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

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class ContactoController {

    @Autowired
    private ContactoService contactoService;

    @GetMapping(path = "/contacts")
    public List<Contacto> listaDeContactos() {
        var contactos = contactoService.listar();
        return contactos;
    }

    @PostMapping(path = "/contact")
    public ResponseEntity<Contacto> crearContacto(Contacto contacto) {
        log.info("Datos del contacto a crear: {}", contacto);
        contactoService.save(contacto);
        return new ResponseEntity<>(contacto, HttpStatus.CREATED);
    }

    @DeleteMapping (path = "/contact/{id}")
    public ResponseEntity<Contacto> borrarContacto(Contacto contacto) {
        log.info("Contacto a borrar: {}", contacto);
        contactoService.delete(contacto);
        return new ResponseEntity<>(contacto, HttpStatus.OK);
    }

    @PutMapping(path = "/contact/{id}")
    public ResponseEntity<Contacto> actualizarContacto(Contacto contacto, @PathVariable("id")Long id) {
        log.info("Contacto a modificar: {}", contacto);
        contactoService.update(id, contacto);
        return new ResponseEntity<>(contacto, HttpStatus.OK);
    }

    @PatchMapping(path = "/contact/name/{id}")
    public ResponseEntity<Contacto> actualizarNombre(Contacto contacto, @PathVariable("id")Long id) {
        log.info("Contacto a modificar: {}", contacto);
        contactoService.updateNombre(id, contacto);
        return new ResponseEntity<>(contacto, HttpStatus.OK);
    }

    @PatchMapping(path = "/contact/email/{id}")
    public void actualizarEmail() { }

    @PatchMapping(path = "/contact/phone/{id}")
    public void actualizarTelefono() { }

    @PatchMapping(path = "/contact/birthDate/{id}")
    public void actualizarFechaNacimiento() { }

//***************************************************+++Pendiente
    @PatchMapping(path = "/contact/Deleted/{id}")
    public void actualizarBorradoLógico() { }
}

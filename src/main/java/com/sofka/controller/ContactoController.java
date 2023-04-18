package com.sofka.controller;

import com.sofka.domain.Contacto;
import com.sofka.service.ContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContactoController {

    @Autowired
    private ContactoService contactoService;

    @GetMapping(path = "/contacts")
    public List<Contacto> listaDeContactos() {
        var contactos = contactoService.listar();
        return contactos;
    }

    @PostMapping(path = "/contact")
    public void crearContacto() { }

    @DeleteMapping (path = "/contact/{id}")
    public void borrarContacto() { }

    @PutMapping(path = "/contact/{id}")
    public void actualizarContacto() { }

    @PatchMapping(path = "/contact/name/{id}")
    public void actualizarNombre() { }

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

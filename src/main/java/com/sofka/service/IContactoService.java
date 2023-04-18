package com.sofka.service;

import com.sofka.domain.Contacto;

import java.util.List;

public interface IContactoService {

    public List<Contacto> listar();

    public Contacto save(Contacto contacto);

    public Contacto update(Long id, Contacto contacto);

    public void delete(Contacto contacto);

}

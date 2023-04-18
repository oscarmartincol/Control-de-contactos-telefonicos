package com.sofka.dao;

import com.sofka.domain.Contacto;
import org.springframework.data.repository.CrudRepository;

public interface ContactoDao extends CrudRepository<Contacto, Long> { }

package com.sofka.service;

import com.sofka.dao.ContactoDao;
import com.sofka.domain.Contacto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContactoService implements IContactoService{

    @Autowired
    private ContactoDao contactoDao;
    @Override
    @Transactional(readOnly = true)
    public List<Contacto> listar() {

        return (List<Contacto>) contactoDao.findAll();
    }

    @Override
    @Transactional
    public Contacto save(Contacto contacto) {

        return contactoDao.save(contacto);
    }

    @Override
    @Transactional
    public Contacto update(Long id, Contacto contacto) {
        contacto.setId(id);
        return contactoDao.save(contacto);
    }

    @Transactional
    public void updateNombre(Long id, Contacto contacto) {
        contactoDao.updateNombre(id, contacto.getNombre());
    }

    @Override
    @Transactional
    public void delete(Contacto contacto) {
        contactoDao.delete(contacto);
    }
}

package com.sofka.service;

import com.sofka.dao.ContactoDao;
import com.sofka.domain.Contacto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Método de servicio que hace el llamado de las sentencias SQL
 */
@Service
public class ContactoService implements IContactoService{

    /**
     * Variable que accede a la interfaz con los metodos que ejecutan las sentencias SQL.
     */
    @Autowired
    private ContactoDao contactoDao;

    /**
     * Método que accede a los datos almacenados en la base de datos
     * @return Sentencia SQL para obtener los contactos almacenados.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Contacto> listar() {
        return (List<Contacto>) contactoDao.findAll();
    }

    /**
     * Método para guardar nuevos contactos en la base de datos.
     * @param contacto contacto que se quiere almacenar
     * @return Sentencia SQL para guardar contactos nuevos
     */
    @Override
    @Transactional
    public Contacto save(Contacto contacto) {
        return contactoDao.save(contacto);
    }

    /**
     * Método para actualizar todos los datos de un contacto almacenado en la base de datos
     * @param id id del contacto
     * @param contacto Datos nuevos que va a tener el contacto
     * @return Sentencia SQL para guardar los datos del contacto
     */
    @Override
    @Transactional
    public Contacto update(Long id, Contacto contacto) {
        contacto.setId(id);
        return contactoDao.save(contacto);
    }

    /**
     * Método para actualizar solamente el borrado lógico del contacto
     * @param id id del contacto
     * @param contacto Contacto que se va a borrar de forma lógica.
     */
    @Transactional
    public void updateBorradoLogico(Long id, Contacto contacto) {
        contactoDao.updateBorradoLogico(id, contacto.isBorrado());
    }

    /**
     * Método que realiza el borrado fisico del contacto
     * @param contacto Contacto que se quiere borrar
     */
    @Override
    @Transactional
    public void delete(Contacto contacto) {
        contactoDao.delete(contacto);
    }
}

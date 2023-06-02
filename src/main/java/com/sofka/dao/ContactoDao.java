package com.sofka.dao;

import com.sofka.domain.Contacto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ContactoDao extends CrudRepository<Contacto, Long> {

    /**
     * Método para actualizar el campo de borrado lógico
     * @param id id del contacto
     * @param borrado representa si el contacto se encuentra borrado de forma lógica.
     */
    @Modifying
    @Query("update Contacto con set con.borrado = :borrado where con.id = :id")
    public void updateBorradoLogico(
            @Param(value = "id") Long id,
            @Param(value = "borrado") boolean borrado
    );
}

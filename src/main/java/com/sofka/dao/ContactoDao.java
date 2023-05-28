package com.sofka.dao;

import com.sofka.domain.Contacto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ContactoDao extends CrudRepository<Contacto, Long> {

    @Modifying
    @Query("update Contacto con set con.nombre = :nombre where con.id = :id")
    public void updateNombre(
            @Param(value = "id") Long id,
            @Param(value = "nombre") String nombre);

    @Modifying
    @Query("update Contacto con set con.borrado = :borrado where con.id = :id")
    public void updateBorradoLogico(
            @Param(value = "id") Long id,
            @Param(value = "borrado") boolean borrado
    );
}

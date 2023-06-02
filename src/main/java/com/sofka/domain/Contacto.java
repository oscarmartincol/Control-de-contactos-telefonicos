package com.sofka.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase que representa el objeto de un contacto de una agenda.
 */
@Data
@Entity
@Table(name = "contacts")
public class Contacto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Representa el id del contacto
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contact")
    private Long id;

    /**
     * Representa el nombre del contacto
     */
    @Column(name = "contact_name")
    private String nombre;

    /**
     * Representa el apellido del contacto
     */
    @Column(name = "contact_lastname")
    private String apellido;

    /**
     * Representa el correo electrónico del contacto
     */
    @Column(name = "contact_email")
    private String email;

    /**
     * Representa el número telfónico del contacto
     */
    @Column(name = "contact_phone")
    private String telefono;

    /**
     * Representa el estado del borrado lógico.
     */
    @Column(name = "deleted")
    private boolean borrado;

    /**
     * Representa la fecha de nacimiento del contacto.
     */
    @Column(name = "contact_birthdate")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fechaDeNacimiento;

}

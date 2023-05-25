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

@Data
@Entity
@Table(name = "contacts")
public class Contacto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contact")
    private Long id;

    @Column(name = "contact_name")
    private String nombre;

    @Column(name = "contact_lastname")
    private String apellido;

    @Column(name = "contact_email")
    private String email;

    @Column(name = "contact_phone")
    private String telefono;

    @Column(name = "deleted")
    private boolean borrado;

    @Column(name = "contact_birthdate")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fechaDeNacimiento;



}

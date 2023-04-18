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
@Table(name = "contactos")
public class Contacto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contactos")
    private Long id;

    @Column(name = "nombre_completo")
    private String nombre;

    @Column(name = "email")
    private String email;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "borrado")
    private boolean borrado;

    @Column(name = "fecha_nacimiento")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fechaDeNacimiento;

}

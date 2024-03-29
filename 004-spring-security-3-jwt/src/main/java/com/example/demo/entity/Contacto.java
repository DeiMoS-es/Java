package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "idContacto")
    private Integer idContacto;

    private String nombre;

    @Column(name = "fechaNac")
    private LocalDate fechaNacimiento;

    private String telefono;
    private String email;
}

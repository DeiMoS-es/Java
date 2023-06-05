package com.example.demo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    private LocalDate fechaNcimiento;

    private String telefono;
    private String email;
}

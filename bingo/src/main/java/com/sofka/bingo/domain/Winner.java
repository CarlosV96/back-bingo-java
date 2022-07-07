package com.sofka.bingo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Entidad winner
 *
 * @author Carlos Valencia <caliche-9696@hotmail.com>
 * @version 1.0.0 2022-07-07
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "winner")
public class Winner {

    /**
     * Identificador de la tupla
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idwinner", nullable = false)
    private Integer id;

    /**
     * Nombre del ganador
     */
    @Column(name = "winner", length = 45)
    private String winner;

}
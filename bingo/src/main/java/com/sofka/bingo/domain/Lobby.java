package com.sofka.bingo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import java.time.Instant;

/**
 * Entidad lobby
 *
 * @author Carlos Valencia <caliche-9696@hotmail.com>
 * @version 1.0.0 2022-07-07
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "lobby")
public class Lobby {

    /**
     * Identificador de la tupla
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlobby", nullable = false)
    private Integer id;

    /**
     * Nombre del jugador o -username-
     */
    @Column(name = "jugador_lobby", length = 45)
    private String jugadorLobby;

    /**
     * Fecha y hora exacta de creación de la tupla.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

}
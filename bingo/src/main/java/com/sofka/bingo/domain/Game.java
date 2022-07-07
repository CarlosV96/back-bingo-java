package com.sofka.bingo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

/**
 * Entidad game
 *
 * @author Carlos Valencia <caliche-9696@hotmail.com>
 * @version 1.0.0 2022-07-07
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "game")
public class Game {

    /**
     * Identificador de la tupla
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idgame", nullable = false)
    private Integer id;

    /**
     * Nombre del jugador o -username-
     */
    @Column(name = "jugador_game", length = 45)
    private String jugadorGame;

}
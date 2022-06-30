package com.sofka.bingo.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idgame", nullable = false)
    private Integer id;

    @Column(name = "jugador_game", length = 45)
    private String jugadorGame;

}
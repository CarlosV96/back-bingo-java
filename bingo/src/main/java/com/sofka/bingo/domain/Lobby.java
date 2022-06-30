package com.sofka.bingo.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "lobby")
public class Lobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlobby", nullable = false)
    private Integer id;

    @Column(name = "jugador_lobby", length = 45)
    private String jugadorLobby;

}
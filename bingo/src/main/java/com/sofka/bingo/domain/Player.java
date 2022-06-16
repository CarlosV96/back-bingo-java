package com.sofka.bingo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Entidad del jugador
 * @version 1.0.0
 * @author Carlos Valencia - caliche-9696@hotmail.com
 * @since 1.0.0
 */
@Entity
@Table(name = "player")
public class Player implements Serializable {

    /**
     * Variable usada para manejar el tema del identificador de la tupla (consecutivo)
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    /**
     * id del jugador
     */
    @Column(name = "id_player", nullable = false, length = 100)
    private String idPlayer;

    /**
     * Punto de enalce entre la entidad game y jugador.
     * Un juego puede tener muchos jugadores
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "game_idgame", nullable = false)
    @JsonBackReference
    private Game gameIdgame;

    /**
     * Punto de enlace entre la entidad jugador y tableGames, o tablas de juego.
     * un jugador puede tener muchas tablas de juego.
     */
    @OneToMany(mappedBy = "player")
    @JsonManagedReference
    private Set<TableGame> tableGames = new LinkedHashSet<>();

}
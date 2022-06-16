package com.sofka.bingo.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Entidad del juego
 * @version 1.0.0
 * @author Carlos Valencia - caliche-9696@hotmail.com
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "game")
public class Game implements Serializable {

    /**
     * Variable usada para manejar el tema del identificador de la tupla (consecutivo)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador de la tupla
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idgame", nullable = false)
    private Integer id;

    /**
     * Punto de enlace entre la entidad Game y Player
     * Un juego o game puede tener muchos player, o jugadores.
     */
    @OneToMany(mappedBy = "gameIdgame")
    @JsonManagedReference
    private Set<Player> players = new LinkedHashSet<>();

}
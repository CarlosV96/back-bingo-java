package com.sofka.bingo.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "winner")
public class Winner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idwinner", nullable = false)
    private Integer id;

    @Column(name = "winner", length = 45)
    private String winner;

}
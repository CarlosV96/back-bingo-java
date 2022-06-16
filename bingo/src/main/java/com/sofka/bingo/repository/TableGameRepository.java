package com.sofka.bingo.repository;

import com.sofka.bingo.domain.Game;
import com.sofka.bingo.domain.TableGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TableGameRepository extends JpaRepository<TableGame, Integer> {

    //Este se supne que es para seleccionar una tabla de juego
    //Pero no se cómo generar la tabla de juego
    @Query(value = "SELECT gameBallBoard.bingoBallGame " +
            "FROM BallBoard gameBallBoard " +
            "WHERE gameBallBoard.idGame = :idGame")
    List<String> findGameBalls(@Param("idGame") Game idGame);
}
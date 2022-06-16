package com.sofka.bingo.repository;

import com.sofka.bingo.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {

    /**
     * Obtener el id del juego
     * @param idGame
     * @return
     */
    @Query(value = "SELECT Game.id " +
            "FROM Game " +
            "WHERE Game.id = :idGame ")
    List<Game> findByGameId(@Param("idGame") Game idGame);

}
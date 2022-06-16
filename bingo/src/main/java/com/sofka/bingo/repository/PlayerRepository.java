package com.sofka.bingo.repository;

import com.sofka.bingo.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PlayerRepository extends JpaRepository<Player, Integer> {

    @Query(value = "SELECT Player.id " +
            "FROM Player " +
            "WHERE Player.id = :game_idgame ")
    List<Player> getPlayer(@Param(value = "game_idgame") Player idGamePlayer);
}
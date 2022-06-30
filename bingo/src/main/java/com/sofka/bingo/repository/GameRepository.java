package com.sofka.bingo.repository;

import com.sofka.bingo.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {
}
package com.sofka.bingo.repository;

import com.sofka.bingo.domain.Lobby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LobbyRepository extends JpaRepository<Lobby, Integer> {
}
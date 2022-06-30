package com.sofka.bingo.repository;

import com.sofka.bingo.domain.Winner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WinnerRepository extends JpaRepository<Winner, Integer> {
}
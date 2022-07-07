package com.sofka.bingo.repository;

import com.sofka.bingo.domain.Winner;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad Winner
 *
 * @author Carlos Valencia <caliche-9696@hotmail.com>
 * @version 1.0.0 2022-07-07
 * @since 1.0.0
 */
public interface WinnerRepository extends JpaRepository<Winner, Integer> {
}
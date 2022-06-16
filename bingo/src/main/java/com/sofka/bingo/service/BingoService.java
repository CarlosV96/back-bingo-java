package com.sofka.bingo.service;

import com.sofka.bingo.domain.Game;
import com.sofka.bingo.domain.Player;
import com.sofka.bingo.domain.TableGame;
import com.sofka.bingo.repository.GameRepository;
import com.sofka.bingo.repository.PlayerRepository;
import com.sofka.bingo.repository.TableGameRepository;
import com.sofka.bingo.service.interfaces.IBingo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BingoService implements IBingo {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TableGameRepository tableGameRepository;

    @Override
    @Transactional
    public Game beginGame(Game game) {
        Game games = new Game();
        games.setId(game.getId());
        GameRepository.save(games);
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Player> getPlayersGame(Integer idGame) {

        return null;
    }

    @Override
    @Transactional
    public TableGame generateTable(TableGame tablegame) {

        return null;
    }
}

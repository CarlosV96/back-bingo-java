package com.sofka.bingo.service.interfaces;

import com.sofka.bingo.domain.Game;
import com.sofka.bingo.domain.Player;
import com.sofka.bingo.domain.TableGame;

import java.util.List;

public interface IBingo {

    /**
     * Para empezar el juego
     * @param game
     * @return
     */
    Game beginGame(Game game);

    /**
     * Obtener los jugadores de un juego por el id.
     * @param idGame id del juego.
     * @return la lista de los jugadores.
     */
    List<Player> getPlayersGame(Integer idGame);



    /**
     * Para obtener una tabla
     * @param tablegame
     * @return
     */
    TableGame generateTable(TableGame tablegame);
    //TableGame generateTable(Game game);
    //Comprobar el ganador

}

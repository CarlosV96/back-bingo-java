package com.sofka.bingo.service.interfaces;

import com.sofka.bingo.domain.Game;
import com.sofka.bingo.domain.Lobby;
import com.sofka.bingo.domain.Winner;

import java.util.List;

/**
 * Interfaz para el servicio del bingo
 *
 * @author Carlos Valencia <caliche-9696@hotmail.com>
 * @version 1.0.0 2022-07-07
 * @since 1.0.0
 */
public interface IBingo {

    /**
     * Inserta los jugadores al lobby
     */
    public Lobby insertPlayersLobby(Lobby lobby);

    /**
     * Obtener los jugadores que se han logueado
     * @return la lista de los jugadores.
     */
    public List<Lobby> getPlayersLobby();

    /**
     * Elimina los jugadores del lobby para pasarlos al game
     */
    public void deletePlayersLobby();

    /**
     * Inserta los jugadores al lobby
     */
    public Game insertPlayersGame(Game game);

    /**
     * Obtener los jugadores logueados
     * @return
     */
    public List<Game> getPlayersGame();

    /**
     * Elimina los jugadores de la tabla game
     */
    public void deletePlayersGame();

    /**
     * Inserta el ganador a la tabla winner
     *
     * @param winner objeto winner a crear.
     * @return Objecto del winner creado
     */
    public Winner winner(Winner winner);

    /**
     * Devuelve el jugador ganador
     * @return El jugador de la tabla winner
     */
    public List<Winner> getWinner();

    /**
     * Elimina el ganador para empezar nuevamente desde cero.
     */
    public void deleteWinner();
}

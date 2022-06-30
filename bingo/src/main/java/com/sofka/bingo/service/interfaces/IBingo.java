package com.sofka.bingo.service.interfaces;

import com.sofka.bingo.domain.Game;
import com.sofka.bingo.domain.Lobby;
import com.sofka.bingo.domain.Winner;

import java.io.IOException;
import java.util.List;

/**
 * Interfaz para el servicio del bingo
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
     *
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
    public List<Game> getPlayersGame(); //throws IOException, InterruptedException;

    //public Game deletePlayersGame(Integer id);


    public Winner winner(Winner winner);

    public List<Winner> getWinner();

    //Winner deleteWinner(Integer id);
}

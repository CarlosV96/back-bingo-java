package com.sofka.bingo.service;

import com.sofka.bingo.domain.Game;
import com.sofka.bingo.domain.Lobby;
import com.sofka.bingo.domain.Winner;
import com.sofka.bingo.repository.GameRepository;
import com.sofka.bingo.repository.LobbyRepository;
import com.sofka.bingo.repository.WinnerRepository;
import com.sofka.bingo.service.interfaces.IBingo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

/**
 * Clase tipo Servicio para el manejo del juego bingo
 *
 * @author Carlos Valencia <caliche-9696@hotmail.com>
 * @version 1.0.0 2022-07-07
 * @since 1.0.0
 */
@Service
public class BingoService implements IBingo {

    /**
     * Repositorio de Game
     */
    @Autowired
    private GameRepository gameRepository;

    /**
     * Repositorio de Lobby
     */
    @Autowired
    private LobbyRepository lobbyRepository;

    /**
     * Repositorio de Winner
     */
    @Autowired
    private WinnerRepository winnerRepository;

    /**
     * Inserta los jugadores al lobby
     *
     * @param lobby objeto lobby a crear.
     * @return Objecto del lobby creado
     */
    @Override
    public Lobby insertPlayersLobby(Lobby lobby) {
        lobby.setCreatedAt(Instant.now());
        return lobbyRepository.save(lobby);
    }

    /**
     * Devuelve una lista con todos los jugadores logueados.
     *
     * @return Todos los jugadores dentro de la tabla lobby
     */
    @Override
    @Transactional(readOnly = true)
    public List<Lobby> getPlayersLobby() {
        return lobbyRepository.findAll();
    }

    /**
     * Borra los jugadores de la tabla lobby
     *
     * @return Objeto del lobby eliminado
     */
    @Override
    @Transactional
    public void deletePlayersLobby() {
        lobbyRepository.deleteAll();
    }

    /**
     * Inserta los jugadores al game
     *
     * @param game objeto game a crear.
     * @return Objecto del game creado
     */
    @Override
    public Game insertPlayersGame(Game game) {
        return gameRepository.save(game);
    }

    /**
     * Devuelve una lista con todos los jugadores logueados.
     *
     * @return Todos los jugadores dentro de la tabla game
     */
    @Override
    @Transactional(readOnly = true)
    public List<Game> getPlayersGame() {
        return gameRepository.findAll();
    }

    /**
     * Borra los jugadores de la tabla game
     *
     * @return Objeto del game eliminado
     */
    @Override
    @Transactional
    public void deletePlayersGame() {
        gameRepository.deleteAll();
    }

    /**
     * Inserta el ganador a la tabla winner
     *
     * @param winner objeto winner a crear.
     * @return Objecto del winner creado
     */
    @Override
    public Winner winner(Winner winner) {
        return winnerRepository.save(winner);
    }

    /**
     * Devuelve el jugador ganador
     *
     * @return El jugador de la tabla winner
     */
    @Override
    @Transactional(readOnly = true)
    public List<Winner> getWinner() {
        return winnerRepository.findAll();
    }

    /**
     * Elimina el ganador para empezar nuevamente de cero.
     */
    @Override
    @Transactional
    public void deleteWinner() {
        winnerRepository.deleteAll();
    }
}

/*
--------------------------------------------------------------------------------------

                SI NECESITARA REALIZAR EL CONSUMO DESDE JAVA AL NODEJS PARA
                TRAER LOS JUGADORES LOGUEADOS CON MONGO, ESTE SERÍA EL PROCESO
                PARA LISTARLOS.

    @Override
    @Transactional(readOnly = true)
    public List getPlayersLobby() throws IOException, InterruptedException {
        final HttpRequest requestPosts = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:4200/get"))
                .build();
        final HttpResponse<String> respuesta = httpClient.send(requestPosts, HttpResponse.BodyHandlers.ofString());
        JSONArray jsonArray = new JSONArray(respuesta.body());
        List<Object> jsonO = jsonArray.toList();
        return jsonO;
        }
*/
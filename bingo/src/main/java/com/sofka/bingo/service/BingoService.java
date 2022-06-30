package com.sofka.bingo.service;

import com.sofka.bingo.domain.Game;
import com.sofka.bingo.domain.Lobby;
import com.sofka.bingo.domain.Winner;
import com.sofka.bingo.repository.GameRepository;
import com.sofka.bingo.repository.LobbyRepository;
import com.sofka.bingo.repository.WinnerRepository;
import com.sofka.bingo.service.interfaces.IBingo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Iterator;
import java.util.List;

@Service
public class BingoService implements IBingo {


    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private LobbyRepository lobbyRepository;

    @Autowired
    private WinnerRepository winnerRepository;

    private HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2).build();
    private final Object[] columna = new Object[]{"Id", "username", "password"};
    private final DefaultTableModel model = new DefaultTableModel(columna, 0);

    /**
     * Inserta los jugadores al lobby
     *
     * @param lobby
     * @return
     */
    @Override
    public Lobby insertPlayersLobby(Lobby lobby) {
        return lobbyRepository.save(lobby);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lobby> getPlayersLobby() {
        return lobbyRepository.findAll();
    }

    @Override
    @Transactional
    public void deletePlayersLobby() {
        lobbyRepository.deleteAll();
    }


    @Override
    public Game insertPlayersGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Game> getPlayersGame() {
        return gameRepository.findAll();

    }

    //Guardar ganador
    @Override
    public Winner winner(Winner winner) {
        return winnerRepository.save(winner);
    }

    //Mostrar Ganador
    @Override
    @Transactional(readOnly = true)
    public List<Winner> getWinner() {
        return winnerRepository.findAll();
    }


}

   /*
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


/* throws IOException, InterruptedException {
        final HttpRequest requestPosts = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:4200/get"))
                .build();
        final HttpResponse<String> respuesta = httpClient.send(requestPosts, HttpResponse.BodyHandlers.ofString());
        JSONArray jsonArray = new JSONArray(respuesta.body());
        List<Object> jsonO = jsonArray.toList();
        //JSONObject jsonObject = jsonArray.getJSONObject(1);

        return jsonO;
        //return jsonObject.get("username");
*/
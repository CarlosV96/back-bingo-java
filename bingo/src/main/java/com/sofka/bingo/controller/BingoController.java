package com.sofka.bingo.controller;

import com.sofka.bingo.domain.Game;
import com.sofka.bingo.domain.Lobby;
import com.sofka.bingo.domain.Winner;
import com.sofka.bingo.service.BingoService;
import com.sofka.bingo.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;

@Slf4j
@RestController
@CrossOrigin
public class BingoController {

    @Autowired
    private BingoService bingoService;

    private Response response = new Response();

    private HttpStatus httpStatus = HttpStatus.OK;

    private HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2).build();
    private final Object[] columna = new Object[]{"Id", "username", "password"};
    private final DefaultTableModel model = new DefaultTableModel(columna, 0);


    @GetMapping(path = "/")
    public ResponseEntity<Response> homeIndex1(HttpServletResponse httpResponse) {
        return getResponseHome(httpResponse);
    }

    @GetMapping(path = "/api/")
    public ResponseEntity<Response> homeIndex2(HttpServletResponse httpResponse) {
        return getResponseHome(httpResponse);
    }

    @GetMapping(path = "/api/v1/")
    public ResponseEntity<Response> homeIndex3(HttpServletResponse httpResponse) {
        return getResponseHome(httpResponse);
    }

    /**
     * Crear un nuevo jugador, sacado de mongo
     *
     * @param lobby Objeto jugador  a crear
     * @return Objeto Response en formato JSON
     */
    @PostMapping(path = "/api/v1/player")
    public ResponseEntity<Response> createPlayersLobby(@RequestBody Lobby lobby) {
        response.restart();
        try {
            log.info("Jugador a crear: {}", lobby);
            response.data = bingoService.insertPlayersLobby(lobby);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Index que me devuelve la lista de los jugadores logueados
     *
     * @return
     */
    @GetMapping(path = "/api/v1/index")
    public ResponseEntity<Response> index() {
        response.restart();
        try {
            //response.data = formApiRest();
            response.data = bingoService.getPlayersLobby();
            httpStatus = httpStatus.OK;

        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    @DeleteMapping(path = "/api/v1/index/delete")
    public ResponseEntity<Response> deletePlayersLobby() {
        response.restart();
        try {
            bingoService.deletePlayersLobby();
            if (response.data == null) {
                response.message = "Lobby finalizado empeiza el juego";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "Jugadores removidos exitosamente";
                httpStatus = HttpStatus.OK;
            }
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    @PostMapping(path = "/api/v1/game")
    public ResponseEntity<Response> createPlayerGame(@RequestBody Game game) {
        response.restart();
        try {
            log.info("Jugador a crear: {}", game);
            response.data = bingoService.insertPlayersGame(game);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }



    /**
     * me devuelve la lista de los jugadores logueados
     *
     * @return
     */
    @GetMapping(path = "/api/v1/index/game")
    public ResponseEntity<Response> game() {
        response.restart();
        try {
            //response.data = consumoGet();
            response.data = bingoService.getPlayersGame();
            httpStatus = httpStatus.OK;

        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }





    /* *
     * Index que me devuelve la lista de los jugadores logueados
     *
     * @return
     *
    @GetMapping(path = "/api/v1/index/game")
    public ResponseEntity<Response> game() {
        response.restart();
        try {
            response.data = bingoService.getPlayersGame();
            httpStatus = httpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

     */


    @PostMapping(path = "/api/v1/winner")
    public ResponseEntity<Response> winner(@RequestBody Winner winner) {
        response.restart();
        try {
            log.info("Winner: {}", winner);
            response.data = bingoService.winner(winner);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    @GetMapping(path = "/api/v1/index/winner")
    public ResponseEntity<Response> winner() {
        response.restart();
        try {
            response.data = bingoService.getWinner();
            httpStatus = httpStatus.OK;

        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }


    /**
     * Administrador para la redirección al controllador /api/v1/index
     *
     * @param httpResponse Objeto HttpServletResponse para el manejo de la redirección
     * @return Objeto Response en formato JSON
     */
    private ResponseEntity<Response> getResponseHome(HttpServletResponse httpResponse) {
        response.restart();
        try {
            httpResponse.sendRedirect("/api/v1/index");
        } catch (IOException exception) {
            response.error = true;
            response.data = exception.getCause();
            response.message = exception.getMessage();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Administrador para las excepciones del sistema
     *
     * @param exception Objeto Exception
     */
    private void getErrorMessageInternal(Exception exception) {
        response.error = true;
        response.message = exception.getMessage();
        response.data = exception.getCause();
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /**
     * Administrador para las excepciones a nivel de SQL con respecto al manejo del acceso a los datos
     *
     * @param exception Objeto DataAccessException
     */
    private void getErrorMessageForResponse(DataAccessException exception) {
        response.error = true;
        if (exception.getRootCause() instanceof SQLException) {
            SQLException sqlEx = (SQLException) exception.getRootCause();
            var sqlErrorCode = sqlEx.getErrorCode();
            switch (sqlErrorCode) {
                case 1062:
                    response.message = "El dato ya está registrado";
                    break;
                case 1452:
                    response.message = "El usuario indicado no existe";
                    break;
                default:
                    response.message = exception.getMessage();
                    response.data = exception.getCause();
            }
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            response.message = exception.getMessage();
            response.data = exception.getCause();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    public Object consumoGet() {
        final HttpRequest requestPosts = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:4200/get"))
                .build();
        try {
            final HttpResponse<String> respuesta = httpClient.send(requestPosts, HttpResponse.BodyHandlers.ofString());

            JSONArray jsonArray = new JSONArray(respuesta.body());
            //JSONObject jsonObject = jsonArray.getJSONObject(1);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                String name = jsonObject1.getString("username");
            }

            return null;


        } catch (IOException | InterruptedException ex) {
            return (ex);
        }
    }

}




    /*
    public void counter() {
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final Runnable runnable = new Runnable() {
            int countdownStarter = 20;
            public void run() {

                System.out.println(countdownStarter);
                countdownStarter--;

                if (countdownStarter < 0) {
                    System.out.println("Timer Over!");
                    scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
    }

}
*/

    /*@GetMapping(path = "/api/v1/counter")
    public ScheduledExecutorService scheduler(HttpServletResponse httpResponse) {
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final Runnable runnable = new Runnable() {
            int countdownStarter = 20;
            public void run() {

                System.out.println(countdownStarter);
                countdownStarter--;

                if (countdownStarter < 0) {
                    System.out.println("Timer Over!");
                    scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
        return scheduler;
    }

/*
     * Me trae los jugadores consumiendo un API de nodeJS
     *
     * @return
     *
    public Object formApiRest() {
        final HttpRequest requestPosts = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:4200/get"))
                .build();
        try {
            final HttpResponse<String> respuesta = httpClient.send(requestPosts, HttpResponse.BodyHandlers.ofString());

            //System.out.println(respuesta.body());
            //Abjo donde esta el cero es la posicion del arreglo que esata trayendo
            JSONArray jsonArray = new JSONArray(respuesta.body());
            //JSONObject jsonObject = jsonArray.getJSONObject(1); //.getJSONObject(1);

            //JSONObject jsonObject = jsonArray.getJSONObject(1);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("username");
                return jsonObject.getString("username");
            }
            //return jsonObject.get("username");
                return null;


        } catch (IOException | InterruptedException ex) {
            return (ex);
        }
    }
     */



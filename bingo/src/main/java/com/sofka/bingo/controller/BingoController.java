package com.sofka.bingo.controller;

import com.sofka.bingo.domain.Game;
import com.sofka.bingo.domain.Lobby;
import com.sofka.bingo.domain.Winner;
import com.sofka.bingo.service.BingoService;
import com.sofka.bingo.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Controlador para la el bingo
 *
 * @author Carlos Valencia <caliche-9696@hotmail.com>
 * @version 1.0.0 2022-07-07
 * @since 1.0.0
 */
@Slf4j
@RestController
@CrossOrigin
public class BingoController {

    /**
     * Servicio para el manejo del juego
     */
    @Autowired
    private BingoService bingoService;

    /**
     * Variable para el manejo de las respuestas de la API
     */
    private Response response = new Response();


    /**
     * Manejo del código HTTP que se responde en las API
     */
    private HttpStatus httpStatus = HttpStatus.OK;

    /**
     * Atención a la dirección raíz del sistema, este redirige a /api/v1/index
     *
     * @param httpResponse Objeto HttpServletResponse usado para redireccionar el controlador
     * @return Objeto Response en formato JSON
     */
    @GetMapping(path = "/")
    public ResponseEntity<Response> homeIndex1(HttpServletResponse httpResponse) {
        return getResponseHome(httpResponse);
    }

    /**
     * Atención a la dirección raíz del sistema, este redirige a /api/v1/index
     *
     * @param httpResponse Objeto HttpServletResponse usado para redireccionar el controlador
     * @return Objeto Response en formato JSON
     */
    @GetMapping(path = "/api/")
    public ResponseEntity<Response> homeIndex2(HttpServletResponse httpResponse) {
        return getResponseHome(httpResponse);
    }

    /**
     * Atención a la dirección raíz del sistema, este redirige a /api/v1/index
     *
     * @param httpResponse Objeto HttpServletResponse usado para redireccionar el controlador
     * @return Objeto Response en formato JSON
     */
    @GetMapping(path = "/api/v1/")
    public ResponseEntity<Response> homeIndex3(HttpServletResponse httpResponse) {
        return getResponseHome(httpResponse);
    }

    /**
     * Index que me devuelve la lista de los jugadores logueados
     *
     * @return Objeto Response en formato JSON
     */
    @GetMapping(path = "/api/v1/index")
    public ResponseEntity<Response> index() {
        response.restart();
        try {
            response.data = bingoService.getPlayersLobby();
            httpStatus = httpStatus.OK;

        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Inserta un nuevo jugador, sacado de mongo en la tabla lobby
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
     * Borra todos los jugadores del lobby registrados.
     *
     * @return Objeto Response en formato JSON
     */
    @DeleteMapping(path = "/api/v1/index/delete")
    public ResponseEntity<Response> deletePlayersLobby() {
        response.restart();
        try {
            bingoService.deletePlayersLobby();
            if (response.data == null) {
                response.message = "No hay jugadores";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "Jugadores removidos exitosamente, empieza el juego.";
                httpStatus = HttpStatus.OK;
            }
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Inserta un nuevo jugador, sacado de mongo en la tabla game
     *
     * @param game Objeto jugador  a crear
     * @return Objeto Response en formato JSON
     */
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
     * Me devuelve la lista de los jugadores logueados de la tabla game
     *
     * @return Objeto Response en formato JSON
     */
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

    /**
     * Borra todos los jugadores del game registrados.
     *
     * @return Objeto Response en formato JSON
     */
    @DeleteMapping(path = "/api/v1/game/delete")
    public ResponseEntity<Response> deletePlayersGame() {
        response.restart();
        try {
            bingoService.deletePlayersGame();
            if (response.data == null) {
                response.message = "No hay jugadores";
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

    /**
     * Inserta el jugador ganador en la tabla winner
     *
     * @param winner Objeto jugador  a crear
     * @return Objeto Response en formato JSON
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

    /**
     * Me devuelve el jugador ganador
     *
     * @return Objeto Response en formato JSON
     */
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
     * Borra el jugador ganador
     *
     * @return Objeto Response en formato JSON
     */
    @DeleteMapping(path = "/api/v1/winner/delete")
    public ResponseEntity<Response> deleteWinner() {
        response.restart();
        try {
            bingoService.deleteWinner();
            if (response.data == null) {
                response.message = "No hay jugadores";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "Jugador removido exitosamente";
                httpStatus = HttpStatus.OK;
            }
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
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
}




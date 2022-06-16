package com.sofka.bingo.controller;

import com.sofka.bingo.service.BingoService;
import com.sofka.bingo.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
public class BingoController {

    @Autowired
    private BingoService bingoService;

    private Response response = new Response();

    private HttpStatus httpStatus = HttpStatus.OK;

    @GetMapping(path = "/")
    public ResponseEntity<Response> homeIndex(HttpServletResponse httpResponse) {
        return getResponseHome(httpResponse);
    }

    /**
     * API que agrega un nuevo usuario
     * @param idPlayer id del jugador
     * @return el id de jugador agregado.
     */
    @PostMapping(path = "/api/v1/waiting-room")
    public ResponseEntity<Response> createGame(@RequestBody String idPlayer) {
        response.restart();
        try {
            response.data = BingoService.beginGame(idPlayer);
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }





    private ResponseEntity<Response> getResponseHome(HttpServletResponse httpResponse) {
        response.restart();
        try {
            httpResponse.sendRedirect("/api/beginGame");
        } catch (IOException exception) {
            response.error = true;
            response.data = exception.getCause();
            response.message = exception.getMessage();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(response, httpStatus);
    }
}

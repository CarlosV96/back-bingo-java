package com.sofka.bingo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


/**
 * Clase main del juego virtual bingo
 *
 * @author Carlos Valencia - caliche-9696@hotmail.com
 * @version 1.0.0 2022-07-07
 * @since 1.0.0
 */
@SpringBootApplication
public class ElGranBudaBingoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElGranBudaBingoApplication.class, args);
    }
}
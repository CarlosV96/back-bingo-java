package com.sofka.bingo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Clase main del juego virtual bingo
 *
 * @author Carlos Valencia - caliche-9696@hotmail.com
 * @version 1.0.0
 * @since 1.0.0
 */
@SpringBootApplication
public class ElGranBudaBingoApplication {


    public static void main(String[] args) {
         SpringApplication.run(ElGranBudaBingoApplication.class, args);



        }
    }

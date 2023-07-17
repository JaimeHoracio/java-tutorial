package com.hache.javatutorial.controllers.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;

/**
 * curl -X GET http://localhost:8080/api/concurrency/virtual-threads
 * curl -X GET http://localhost:8080/api/concurrency/platform-threads
 **/

/**
 * Metodos que crean un hilos concurrentes en cada iteracion.
 * Estos hilos imprimen el iterador y se duermen.
 * Notar que el metodo terminará una vez que terminé la iteracion,
 * no espera a que terminen todos los hilos.
 * Todos estos hilos se ejecutan concurrentemente.
 */
@Slf4j
@RestController
@RequestMapping("/api/concurrency")
public class VirtualThreadsController {

    private final Integer MAX_ITER = 1_000;
    private final Integer SLEEP_MILLS = 5_000;

    @GetMapping("/platform-threads")
    public String executeExamplePlatformThreads() {
        log.info("Platform Threads");
        final var start = Instant.now();

        for (int i = 0; i < MAX_ITER; i++) {
            if (i % 1000 == 0) {
                log.info("Intervalo: " + i);
            }

            final String iter = String.valueOf(i);
            new Thread(() -> {
                sleepThread(iter);
            }).start();
        }
        final Long duration = Duration.between(start, Instant.now()).toMillis();
        log.info("Tiempo milisegundos: " + duration);

        return "Tiempo milisegundos: " + duration;
    }

    @GetMapping("/virtual-threads")
    public String executeExampleVirtualThreads() {
        log.info("Virtual Threads");
        final var start = Instant.now();

        for (int i = 0; i < MAX_ITER; i++) {
            if (i % 1000 == 0) {
                log.info("Intervalo: " + i);
            }

            final String iter = String.valueOf(i);
            Thread.startVirtualThread(() -> {
                sleepThread(iter);
            });
        }
        final long duration = Duration.between(start, Instant.now()).toMillis();
        log.info("Tiempo milisegundos: " + duration);

        return "Tiempo milisegundos: " + duration;
    }




    private void sleepThread(final String iter) {
        try {
            log.info(iter);
            Thread.sleep(SLEEP_MILLS);
        } catch (InterruptedException e) {
            log.info("Error: " + e.getMessage());
        }
    }

}

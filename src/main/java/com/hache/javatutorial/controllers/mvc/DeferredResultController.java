package com.hache.javatutorial.controllers.mvc;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.time.Duration;
import java.time.Instant;

/**
 * curl -X GET http://localhost:8080/api/resolve/blocking
 * curl -X GET http://localhost:8080/api/resolve/non-blocking
 *
 * Con Apache Bench https://diamantidis.github.io/2020/07/15/load-testing-with-apache-bench
 *
 * ab -n 100 -c 100 http://localhost:8080/api/resolve/non-blocking
 *
 * Donde -n numeros de request y -c de esos request cuantos ejecutan concurrentes.
 * En este caso tiramos 100 request y los 100 ejecutan concurrentes.
 */

@Slf4j
@RestController
@RequestMapping("/api/resolve")
public class DeferredResultController {

    @GetMapping("/blocking")
    public ResponseEntity<String> resolveBlocking() {

        log.info("Blocking sleep");
        final var start = Instant.now();
        sleepThread();
        final Long mills = Duration.between(start, Instant.now()).toMillis();
        log.info("Resultado Blocking");
        return ResponseEntity.ok("Resultado Blocking - mills: " + mills);
    }

    /**
     * El hilo que atiendo la peticion no queda esperando por el resultado, se libera.
     * Cuando el resultado esta pronto es agregado al deferredResult,
     * este lo agrega al response del metodo y este termina.
     */
    @GetMapping("non-blocking")
    public DeferredResult<ResponseEntity<String>> resolveNonBlocking() {

        log.info("Non-Blocking sleep");
        final var start = Instant.now();

        DeferredResult<ResponseEntity<String>> result = new DeferredResult<>();

        Thread.startVirtualThread(() -> {
            sleepThread();
            final Long mills = Duration.between(start, Instant.now()).toMillis();
            result.setResult(ResponseEntity.ok("Resultado Blocking - mills: " + mills));
        });
        
        log.info("Resultado Non-Blocking");

        return result;
    }

    private void sleepThread() {
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            log.info("Error en el hilo: " + e.getMessage());
        }
    }

}

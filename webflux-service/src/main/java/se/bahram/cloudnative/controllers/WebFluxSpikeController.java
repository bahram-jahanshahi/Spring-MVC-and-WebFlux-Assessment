package se.bahram.cloudnative.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/api/v1/web-flux")
public class WebFluxSpikeController {

    @Value("${spike.waiting.time}")
    private Long waitingTime = 10L;

    @GetMapping("/spike")
    private Mono<String> spike() {

        return Mono.delay(Duration.ofMillis(waitingTime))
                .then(Mono.just("Hello WebFlux in " + waitingTime + " milliseconds"));
    }

    private void delay(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

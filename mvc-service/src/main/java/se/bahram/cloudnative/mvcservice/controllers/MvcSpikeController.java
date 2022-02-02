package se.bahram.cloudnative.mvcservice.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mvc")
public class MvcSpikeController {

    @Value("${spike.waiting.time}")
    private Long waitingTime = 10L;

    @GetMapping("/spike")
    private ResponseEntity<String> spike() {
        delay(waitingTime);
        return ResponseEntity.ok("Hello MVC spike in " + waitingTime + " milliseconds");
    }

    private void delay(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

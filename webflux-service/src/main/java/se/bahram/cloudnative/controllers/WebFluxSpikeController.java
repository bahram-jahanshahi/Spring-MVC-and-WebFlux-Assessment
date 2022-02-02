package se.bahram.cloudnative.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;

import java.time.Duration;

@RestController
@RequestMapping("/api/v1/web-flux")
public class WebFluxSpikeController {

    @Value("${spike.waiting.time}")
    private Integer waitingTime = 10;

    @GetMapping("/spike")
    private Mono<String> spike() {

        long t1 = System.currentTimeMillis();
        Flux<CatResult> tuple3Flux = Flux.zip(nameById(14), ageById(14), colorById(14))
                .map(tuple3 -> new CatResult(tuple3.getT1(), tuple3.getT2(), tuple3.getT3()));
        tuple3Flux.subscribe(System.out::println);
        long t2 = System.currentTimeMillis();
        System.out.println("Time: " + (t2 - t1) + " ms");

        return Mono.delay(Duration.ofMillis(waitingTime))
                .then(Mono.just("Hello WebFlux in " + waitingTime + " milliseconds"));
    }

    private Mono<String> nameById(int id) {
        System.out.println("nameById ...");
        //delay(waitingTime);
        return Mono.delay(Duration.ofSeconds(10)).map(s -> "Catie");
    }

    private Mono<Integer> ageById(int id) {
        System.out.println("ageById ... ");
        //delay(waitingTime);
        return Mono.delay(Duration.ofSeconds(8)).map(s -> 3);
    }

    private Mono<String> colorById(int id) {
        System.out.println("colorById ...");
        //delay(waitingTime);
        return Mono.delay(Duration.ofSeconds(9)).map(s -> "White");
    }

    private void delay(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class CatResult {
        private final String name;
        private final Integer age;
        private final String color;

        CatResult(String name, Integer age, String color) {
            this.name = name;
            this.age = age;
            this.color = color;
        }

        @Override
        public String toString() {
            return "CatResult{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", color='" + color + '\'' +
                    '}';
        }
    }
}

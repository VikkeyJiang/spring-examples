package cn.vikkey.cloud.gateway.example;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;

public class MonoTest {

    @Test
    public void testDelayUntil() {
        Mono.just("examples").delayUntil(s -> Mono.just(s + " ssss abcd").checkpoint("inner").log()).log().subscribe(System.out::println);
    }
}

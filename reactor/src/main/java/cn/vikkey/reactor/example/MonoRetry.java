package cn.vikkey.reactor.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicInteger;

public class MonoRetry {

    private static final Logger logger = LoggerFactory.getLogger(MonoRetry.class);

    public static void main(String[] args) {

        final int initState = 1;
        AtomicInteger state = new AtomicInteger(initState);
        Mono<String> map = Mono.defer(() -> {
            if (state.get() == 1 || state.get() == 2) {
                state.incrementAndGet();
                return Mono.error(new UnsupportedOperationException());
            } else {
                return Mono.just(state.get());
            }
        }).map(String::valueOf);

        map.retry().subscribe(logger::info);
        state.set(initState);

        map.retry(3).subscribe(logger::info);
        state.set(initState);
        map.retry(1).subscribe(logger::info, ex -> logger.info("", ex));
        state.set(initState);

    }
}

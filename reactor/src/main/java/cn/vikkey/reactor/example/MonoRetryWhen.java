package cn.vikkey.reactor.example;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.util.concurrent.atomic.AtomicInteger;

public class MonoRetryWhen {
    private static final Logger logger = LoggerFactory.getLogger(MonoRetryWhen.class);

    public static void main(String[] args) {
        final int initState = 1;
        final int maxState = 3;
        AtomicInteger state = new AtomicInteger(initState);
        Mono<String> map = Mono.defer(() -> {
            if (state.get() < maxState) {
                state.incrementAndGet();
                return Mono.error(new UnsupportedOperationException());
            } else {
                return Mono.just(state.get());
            }
        }).map(String::valueOf);

        map.retryWhen(new Retry() {
            @Override
            public Publisher<?> generateCompanion(Flux<RetrySignal> retrySignals) {
                return retrySignals.any(retrySignal -> {
                    return retrySignal.failure() instanceof UnsupportedOperationException;
                });
            }
        }).subscribe(logger::info, ex -> logger.info("", ex));
    }
}

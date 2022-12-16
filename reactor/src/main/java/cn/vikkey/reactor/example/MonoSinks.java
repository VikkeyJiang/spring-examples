package cn.vikkey.reactor.example;

import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;

public class MonoSinks {

    private static final Logger logger = LoggerFactory.getLogger(MonoSinks.class);

    public static void main(String[] args) {

        Sinks.Many<Long> all = Sinks.many().replay().<Long>all();
        Flux<Long> flux = all.asFlux();

        flux.publishOn(Schedulers.parallel()).subscribe(l -> System.out.println(Thread.currentThread().getName() + "\t" + l));
        flux.publishOn(Schedulers.parallel()).subscribe(new CoreSubscriber<Long>() {
            Subscription internal;

            @Override
            public void onSubscribe(Subscription s) {
                internal = s;
                internal.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println(Thread.currentThread().getName() + "\t" + aLong);
                internal.request(Long.MAX_VALUE);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
        all.emitNext(1L, (signalType, emitResult) -> false);
        all.emitNext(2L, Sinks.EmitFailureHandler.FAIL_FAST);
        all.emitNext(3L, Sinks.EmitFailureHandler.FAIL_FAST);
        all.emitNext(4L, Sinks.EmitFailureHandler.FAIL_FAST);
        logger.info("ReactorApplication Running");
    }
}

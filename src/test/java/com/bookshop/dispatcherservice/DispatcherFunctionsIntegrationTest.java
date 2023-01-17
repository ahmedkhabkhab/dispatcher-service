package com.bookshop.dispatcherservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.function.Function;

@FunctionalSpringBootTest
public class DispatcherFunctionsIntegrationTest {

    @Autowired
    private FunctionCatalog catalog;

    @Test
    public void packAndLabelOrder() {
        Function<OrderAcceptedMessage, Flux<OrderDispatchedMessage>> packAndLabel = catalog.lookup(Function.class, "pack|label");
        long orderId = 100L;
        StepVerifier.create(packAndLabel.apply(new OrderAcceptedMessage(orderId)))
                .expectNextMatches(dispatchedOrder ->
                        dispatchedOrder.equals(new OrderDispatchedMessage(orderId)))
                .verifyComplete();
    }


}

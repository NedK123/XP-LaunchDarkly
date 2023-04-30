package com.example.xplaunchdarkly.api;

import com.launchdarkly.sdk.LDContext;
import com.launchdarkly.sdk.server.LDClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static java.lang.String.*;

@RestController
@AllArgsConstructor
@Slf4j
public class TicketsController implements TicketsAPi {
    static final String FEATURE_FLAG_KEY = "can-reserve-ticket";

    private LDClient client;

    @Override
    public ResponseEntity<String> reserveTicket() {
        LDContext context = LDContext.builder("example-user-key").name("Sandy").build();

        boolean flagValue = client.boolVariation(FEATURE_FLAG_KEY, context, false);

        return ResponseEntity.created(URI.create(format("/tickets/reservation/%s", flagValue))).build();
    }

}

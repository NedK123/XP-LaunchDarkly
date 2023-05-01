package com.example.xplaunchdarkly.api;

import com.launchdarkly.sdk.LDContext;
import com.launchdarkly.sdk.server.LDClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

import static java.lang.String.format;

@RestController
@AllArgsConstructor
@Slf4j
public class TicketsController implements TicketsAPi {
    static final String FEATURE_FLAG_KEY = "can-reserve-ticket";

    private LDClient client;

    @Override
    public ResponseEntity<String> reserveTicket() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        LDContext context = LDContext.builder(generateKey(username)).name(username).build();

        boolean canAccessFeature = client.boolVariation(FEATURE_FLAG_KEY, context, false);

        if (canAccessFeature) {
            return ResponseEntity.created(URI.create(format("/tickets/reservation/%s", UUID.randomUUID()))).build();
        }
        return ResponseEntity.notFound().build();

    }

    private String generateKey(String username) {
        return format("%s-key", username);
    }

}

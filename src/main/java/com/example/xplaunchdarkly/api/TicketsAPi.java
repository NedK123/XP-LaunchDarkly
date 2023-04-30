package com.example.xplaunchdarkly.api;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tickets/")
public interface TicketsAPi {
    @PostMapping("/reserve")
    ResponseEntity<String> reserveTicket();
}

package com.annakhuseinova.springbootvirtualthreadsexample.client;

import com.annakhuseinova.springbootvirtualthreadsexample.dto.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

@RequiredArgsConstructor
public class EventServiceClient {

    private final RestClient client;

    public List<Event> getEvents(String airportCode){
        return this.client.get()
                .uri("{airportCode}", airportCode)
                .retrieve()
                .body(new ParameterizedTypeReference<List<Event>>() {});
    }
}

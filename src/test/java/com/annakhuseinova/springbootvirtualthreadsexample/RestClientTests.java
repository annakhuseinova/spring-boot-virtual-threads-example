package com.annakhuseinova.springbootvirtualthreadsexample;

import com.annakhuseinova.springbootvirtualthreadsexample.dto.Accommodation;
import com.annakhuseinova.springbootvirtualthreadsexample.dto.FlightReservationRequest;
import com.annakhuseinova.springbootvirtualthreadsexample.dto.FlightReservationResponse;
import com.annakhuseinova.springbootvirtualthreadsexample.dto.Weather;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.List;

public class RestClientTests {

    private static final Logger log = LoggerFactory.getLogger(RestClientTests.class);

    // RestClient is a new (as of Spring 6.1) synchronous http client which is immutable and thread-safe
    @Test
    void simpleGet(){
        RestClient restClient = RestClient.create();

        Weather response = restClient.get()
                .uri("http://localhost:7070/sec07/weather/LAS")
                .retrieve()
                .body(Weather.class);

        log.info("response: {}", response);
    }

    @Test
    void baseUrl(){
        RestClient restClient = RestClient
                .builder()
                .baseUrl("http://localhost:7070/sec02/weather/")
                .build();

        Weather response = restClient.get()
                .uri("{airportCode}", "LAS")
                .retrieve()
                .body(Weather.class);

        log.info("response: {}", response);
    }

    @Test
    void listResponse(){
        RestClient restClient = RestClient
                .builder()
                .baseUrl("http://localhost:7070/sec02/accommodations/")
                .build();

        List<Accommodation> response = restClient.get()
                .uri("{airportCode}", "LAS")
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});

        log.info("response: {}", response);
    }

    @Test
    void postRequest(){
        RestClient restClient = RestClient
                .builder()
                .baseUrl("http://localhost:7070/sec03/flight/reserve/")
                .build();

        var request = new FlightReservationRequest("ATL", "LAS", "UA789", LocalDate.now());
        var response =
                restClient.post()
                        .body(request)
                        .retrieve()
                        .body(FlightReservationResponse.class);

        log.info("response: {}", response);
    }
}

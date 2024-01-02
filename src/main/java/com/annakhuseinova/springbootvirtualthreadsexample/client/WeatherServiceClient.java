package com.annakhuseinova.springbootvirtualthreadsexample.client;

import com.annakhuseinova.springbootvirtualthreadsexample.dto.Weather;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class WeatherServiceClient {

    private final RestClient restClient;

    public Weather getWeather(String airportCode){
        return this.restClient.get()
                .uri("{airportCode}", airportCode)
                .retrieve()
                .body(Weather.class);
    }
}

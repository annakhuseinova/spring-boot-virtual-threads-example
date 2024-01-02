package com.annakhuseinova.springbootvirtualthreadsexample.config;

import com.annakhuseinova.springbootvirtualthreadsexample.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ServiceClientsConfig {

    private static final Logger log = LoggerFactory.getLogger(ServiceClientsConfig.class);

    @Bean
    public AccommodationServiceClient accommodationServiceClient(@Value("${accommodation.service.url}") String baseUrl){
        return new AccommodationServiceClient(buildRestClient(baseUrl));
    }

    @Bean
    public EventServiceClient eventServiceClient(@Value("${event.service.url}") String baseUrl){
        return new EventServiceClient(buildRestClient(baseUrl));
    }

    @Bean
    public FlightReservationServiceClient flightReservationServiceClient(@Value("${flight-reservation.service.url}") String baseUrl){
        return new FlightReservationServiceClient(buildRestClient(baseUrl));
    }

    @Bean
    public FlightSearchServiceClient flightSearchServiceClient(@Value("${flight-search.service.url}") String baseUrl){
        return new FlightSearchServiceClient(buildRestClient(baseUrl));
    }

    @Bean
    public LocalRecommendationServiceClient localRecommendationServiceClient(@Value("${local-recommendation.service.url}") String baseUrl){
        return new LocalRecommendationServiceClient(buildRestClient(baseUrl));
    }

    @Bean
    public TransportationServiceClient transportationServiceClient(@Value("${transportation.service.url}") String baseUrl){
        return new TransportationServiceClient(buildRestClient(baseUrl));
    }

    @Bean
    public WeatherServiceClient weatherServiceClient(@Value("${weather.service.url}") String baseUrl){
        return new WeatherServiceClient(buildRestClient(baseUrl));
    }

    private RestClient buildRestClient(String baseUrl){
        log.info("base url: {}", baseUrl);
        return RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }
}

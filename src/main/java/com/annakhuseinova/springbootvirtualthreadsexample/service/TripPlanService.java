package com.annakhuseinova.springbootvirtualthreadsexample.service;

import com.annakhuseinova.springbootvirtualthreadsexample.client.*;
import com.annakhuseinova.springbootvirtualthreadsexample.dto.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class TripPlanService {

    private static final Logger log = LoggerFactory.getLogger(TripPlanService.class);

    private final EventServiceClient eventServiceClient;
    private final WeatherServiceClient weatherServiceClient;
    private final AccommodationServiceClient accommodationServiceClient;
    private final TransportationServiceClient transportationServiceClient;
    private final LocalRecommendationServiceClient localRecommendationServiceClient;
    private final ExecutorService executorService;

    public TripPlan getTripPlan(String airportCode){
        Future<List<Event>> events = this.executorService.submit(() -> this.eventServiceClient.getEvents(airportCode));
        Future<Weather> weather = this.executorService.submit(() -> this.weatherServiceClient.getWeather(airportCode));
        Future<List<Accommodation>> accommodations = this.executorService.submit(() -> this.accommodationServiceClient.getAccommodations(airportCode));
        Future<Transportation> transportation = this.executorService.submit(() -> this.transportationServiceClient.getTransportation(airportCode));
        Future<LocalRecommendations> recommendations = this.executorService.submit(() -> this.localRecommendationServiceClient.getRecommendations(airportCode));
        return new TripPlan(airportCode,
                getOrElse(accommodations, Collections.emptyList()),
                getOrElse(weather, null),
                getOrElse(events, Collections.emptyList()),
                getOrElse(recommendations, null),
                getOrElse(transportation, null));
    }


    private <T> T getOrElse(Future<T> future, T defaultValue){
        try {
            return future.get();
        } catch (Exception e){
            log.error("error", e);
        }
        return defaultValue;
    }
}

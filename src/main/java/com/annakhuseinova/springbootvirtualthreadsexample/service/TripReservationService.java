package com.annakhuseinova.springbootvirtualthreadsexample.service;

import com.annakhuseinova.springbootvirtualthreadsexample.client.FlightReservationServiceClient;
import com.annakhuseinova.springbootvirtualthreadsexample.client.FlightSearchServiceClient;
import com.annakhuseinova.springbootvirtualthreadsexample.dto.Flight;
import com.annakhuseinova.springbootvirtualthreadsexample.dto.FlightReservationRequest;
import com.annakhuseinova.springbootvirtualthreadsexample.dto.FlightReservationResponse;
import com.annakhuseinova.springbootvirtualthreadsexample.dto.TripReservationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TripReservationService {

    private final FlightSearchServiceClient searchServiceClient;
    private final FlightReservationServiceClient reservationServiceClient;

    public FlightReservationResponse reserve(TripReservationRequest request){
        List<Flight> flights = this.searchServiceClient.getFlights(request.departure(), request.arrival());
        Optional<Flight> bestDeal = flights.stream().min(Comparator.comparingInt(Flight::price));
        var flight = bestDeal.orElseThrow(()-> new IllegalStateException("no flights found"));
        var reservationRequest = new FlightReservationRequest(request.departure(), request.arrival(), flight.flightNumber(),
                request.date());
        return this.reservationServiceClient.reserve(reservationRequest);
    }
}

package com.annakhuseinova.springbootvirtualthreadsexample.client;

import com.annakhuseinova.springbootvirtualthreadsexample.dto.FlightReservationRequest;
import com.annakhuseinova.springbootvirtualthreadsexample.dto.FlightReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class FlightReservationServiceClient {

    private final RestClient restClient;

    public FlightReservationResponse reserve(FlightReservationRequest request){
        return this.restClient.post()
                .body(request)
                .retrieve()
                .body(FlightReservationResponse.class);
    }
}

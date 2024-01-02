package com.annakhuseinova.springbootvirtualthreadsexample.controller;

import com.annakhuseinova.springbootvirtualthreadsexample.dto.FlightReservationResponse;
import com.annakhuseinova.springbootvirtualthreadsexample.dto.TripPlan;
import com.annakhuseinova.springbootvirtualthreadsexample.dto.TripReservationRequest;
import com.annakhuseinova.springbootvirtualthreadsexample.service.TripPlanService;
import com.annakhuseinova.springbootvirtualthreadsexample.service.TripReservationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("trip")
@RequiredArgsConstructor
public class TripController {

    private final static Logger log = LoggerFactory.getLogger(TripController.class);
    private final TripPlanService planService;
    private final TripReservationService reservationService;

    @GetMapping("{airportCode}")
    public TripPlan planTrip(@PathVariable String airportCode){
        log.info("airport code: {}, is Virtual: {}", airportCode, Thread.currentThread().isVirtual());
        return this.planService.getTripPlan(airportCode);
    }

    @PostMapping("reserve")
    public FlightReservationResponse reserveFlight(@RequestBody TripReservationRequest request){
        return this.reservationService.reserve(request);
    }
}

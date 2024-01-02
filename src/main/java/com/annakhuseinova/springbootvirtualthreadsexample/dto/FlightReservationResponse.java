package com.annakhuseinova.springbootvirtualthreadsexample.dto;

import java.time.LocalDate;
import java.util.UUID;

public record FlightReservationResponse(UUID reservationId,
                                        String departure,
                                        String arrival,
                                        String flightNumber,
                                        LocalDate tripDate,
                                        int price) {
}

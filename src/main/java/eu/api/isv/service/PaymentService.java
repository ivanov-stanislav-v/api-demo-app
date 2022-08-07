package eu.api.isv.service;

import eu.api.isv.service.model.PazeRequest;
import eu.api.isv.service.model.PazeResponse;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

public interface PaymentService {

    ResponseEntity<PazeResponse> createPayment(@Valid PazeRequest request);
}

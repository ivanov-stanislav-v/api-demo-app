package eu.paze.isv.service;

import eu.paze.isv.service.model.PazeRequest;
import eu.paze.isv.service.model.PazeResponse;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

public interface PaymentService {

    ResponseEntity<PazeResponse> createPayment(@Valid PazeRequest request);
}

package eu.paze.isv;

import eu.paze.isv.model.PazeRequest;
import eu.paze.isv.model.PazeResponse;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

public interface PaymentService {

    ResponseEntity<PazeResponse> createPayment(@Valid PazeRequest request);
}

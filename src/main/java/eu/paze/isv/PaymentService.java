package eu.paze.isv;

import eu.paze.isv.model.PazeRequest;
import eu.paze.isv.model.PazeResponse;

public interface PaymentService {

    PazeResponse createPayment(PazeRequest request);
}

package eu.paze.isv;

import eu.paze.isv.model.PazeRequest;
import eu.paze.isv.model.PazeResponse;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class PaymentServiceBean implements PaymentService {
    @Override
    public PazeResponse createPayment(@Valid PazeRequest request) {
        return null;
    }
}

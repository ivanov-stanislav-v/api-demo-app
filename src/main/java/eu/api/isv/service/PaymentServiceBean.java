package eu.api.isv.service;

import eu.api.isv.service.model.PazeRequest;
import eu.api.isv.service.model.PazeResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class PaymentServiceBean implements PaymentService {

    @Autowired
    private RestClient restClient;

    @Override
    @SneakyThrows
    public ResponseEntity<PazeResponse> createPayment(@Valid PazeRequest request) {
        return restClient.post(request, PazeResponse.class);
    }
}

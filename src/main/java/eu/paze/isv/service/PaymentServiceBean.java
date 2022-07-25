package eu.paze.isv.service;

import eu.paze.isv.service.model.PazeRequest;
import eu.paze.isv.service.model.PazeResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;

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

package eu.paze.isv;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.paze.isv.model.PazeRequest;
import eu.paze.isv.model.PazeResponse;
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

    @Value("${app.token:}")
    private String bearerToken;

    @Autowired
    private RestClient restClient;

    @Autowired
    ObjectMapper om;

    private HttpHeaders headers;

    @Override
    @SneakyThrows
    public ResponseEntity<PazeResponse> createPayment(@Valid PazeRequest request) {
        HttpEntity<PazeRequest> httpEntity = new HttpEntity<>(request, headers);

        return restClient.post(httpEntity, PazeResponse.class);
    }

    @PostConstruct
    public void setHeaders() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(bearerToken);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
    }
}

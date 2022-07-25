package eu.paze.isv.service;

import eu.paze.isv.service.model.PazeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class RestClientBean implements RestClient {

    @Value("${app.baseUrl}")
    private String baseUrl;

    @Value("${app.token:}")
    private String bearerToken;

    private HttpHeaders headers;

    private final RestTemplate restTemplate;

    @Autowired
    public RestClientBean() {
        restTemplate = new RestTemplate();
    }

    @Override
    public <M, T> ResponseEntity<T> post(M request, Class<T> respType) {
        HttpEntity<M> httpEntity = new HttpEntity<>(request, headers);

        return restTemplate.exchange(baseUrl, HttpMethod.POST, httpEntity, respType);
    }

    @PostConstruct
    public void setHeaders() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(bearerToken);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
    }
}

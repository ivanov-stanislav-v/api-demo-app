package eu.paze.isv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClientBean implements RestClient {

    @Value("${app.baseUrl}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    @Autowired
    public RestClientBean() {
        restTemplate = new RestTemplate();
    }

    @Override
    public <T> ResponseEntity<T> post(HttpEntity<?> request, Class<T> respType) {
        return restTemplate.exchange(baseUrl, HttpMethod.POST, request, respType);
    }
}

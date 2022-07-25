package eu.paze.isv;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

public interface RestClient {

    <T> ResponseEntity<T> post(HttpEntity<?> request, Class<T> respType);
}

package eu.api.isv.service;

import org.springframework.http.ResponseEntity;

public interface RestClient {

    <M, T> ResponseEntity<T> post(M request, Class<T> respType);
}

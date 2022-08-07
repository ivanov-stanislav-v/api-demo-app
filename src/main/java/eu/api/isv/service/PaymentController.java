package eu.api.isv.service;

import eu.api.isv.service.model.ApiRequest;
import eu.api.isv.service.model.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Slf4j
@Valid
@Controller
@RequiredArgsConstructor
public class PaymentController {

    @Autowired
    private RestClient restClient;

    @PostMapping(value = "/payments/")
    public @ResponseBody ResponseEntity<ApiResponse> createPayment(@Valid @RequestBody ApiRequest request) {
        log.info("-> createPayment: {}", request);

        return restClient.post(request, ApiResponse.class);
    }
}

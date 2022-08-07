package eu.api.isv.service;

import eu.api.isv.service.model.Currency;
import eu.api.isv.service.model.PaymentType;
import eu.api.isv.service.model.PazeRequest;
import eu.api.isv.service.model.PazeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.math.BigDecimal;

@Slf4j
@Valid
@Controller
@RequiredArgsConstructor
public class PaymentController {

    @Autowired
    private final PaymentService paymentService;

    @PostMapping(value = "/payments/")
    public @ResponseBody ResponseEntity<PazeResponse> createPayment(@Valid @RequestBody PazeRequest request) {
        log.info("-> createPayment: {}", request);

        return paymentService.createPayment(request);
    }
}

package eu.paze.isv;

import eu.paze.isv.model.Currency;
import eu.paze.isv.model.PaymentType;
import eu.paze.isv.model.PazeRequest;
import eu.paze.isv.model.PazeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PaymentController {

    @Autowired
    private final PaymentService paymentService;

    @PostMapping(value = "/payments/{amount:.+}")
    public @ResponseBody ResponseEntity<PazeResponse> createPayment(@PathVariable BigDecimal amount) {
        log.info("-> createPayment, amount={}", amount);

        return paymentService.createPayment(
                PazeRequest.builder()
                        .amount(amount)
                        .paymentType(PaymentType.DEPOSIT)
                        .currency(Currency.EUR)
                        .build()
        );
    }
}

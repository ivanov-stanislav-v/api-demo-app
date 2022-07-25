package eu.paze.isv;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

@Controller
public class PaymentController {

    @PostMapping(value = "/payments/{amount:.+}")
    public ResponseEntity<?> createPayment(@PathVariable BigDecimal amount) {
        return null;
    }
}

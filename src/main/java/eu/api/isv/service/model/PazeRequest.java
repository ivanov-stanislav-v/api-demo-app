package eu.api.isv.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PazeRequest {

    @Builder.Default
    private PaymentType paymentType = PaymentType.DEPOSIT;

    @Positive
    @Digits(integer = 6, fraction = 2)
    private BigDecimal amount;

    @Builder.Default
    private Currency currency = Currency.EUR;
}

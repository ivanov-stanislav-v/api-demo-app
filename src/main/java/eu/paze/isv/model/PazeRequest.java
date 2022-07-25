package eu.paze.isv.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@Builder
public class PazeRequest {

    @NotNull
    private PaymentType paymentType;

    @Positive
    @Digits(integer = 6, fraction = 2)
    private BigDecimal amount;

    @NotNull
    private Currency currency;
}

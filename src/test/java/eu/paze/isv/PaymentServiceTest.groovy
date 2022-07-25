package eu.paze.isv

import eu.paze.isv.model.Currency
import eu.paze.isv.model.PaymentType
import eu.paze.isv.model.PazeRequest
import eu.paze.isv.model.PazeResponse
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Unroll

import javax.validation.ConstraintViolationException

class PaymentServiceTest extends CommonSpecification {

    @Autowired
    PaymentService paymentService

    @MockBean
    RestClient client

    def setup() {
        Mockito.when(client.post(Mockito.any(), Mockito.eq(PazeResponse.class)))
                .thenReturn(new ResponseEntity(HttpStatus.OK))
    }

    @Unroll
    def "Success validation"() {
        setup:
        def request = PazeRequest.builder()
                .amount(amount)
                .paymentType(PaymentType.DEPOSIT)
                .currency(Currency.EUR)
                .build()

        expect:
        paymentService.createPayment(request).statusCode == HttpStatus.OK

        where:
        amount << [0.01, 999999.99]
    }

    def "Failed validation"() {
        setup:
        def request = PazeRequest.builder()
                .amount(0.0)
                .paymentType(PaymentType.DEPOSIT)
                .currency(Currency.EUR)
                .build()

        when:
        paymentService.createPayment(request)

        then:
        thrown(ConstraintViolationException)

        where:
        amount << [-0.01, 0.0, 0.001, 9999999.99]
    }
}

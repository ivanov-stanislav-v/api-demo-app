package eu.paze.isv

import eu.paze.isv.model.Currency
import eu.paze.isv.model.PaymentType
import eu.paze.isv.model.PazeRequest
import eu.paze.isv.model.PazeResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.client.HttpClientErrorException
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest
class RestClientTest extends Specification {

    @Autowired
    PaymentService service

    @Autowired
    RestClient restClient

    @Shared
    PazeRequest request

    def setupSpec() {
        request = PazeRequest.builder()
                .amount(0.01)
                .paymentType(PaymentType.DEPOSIT)
                .currency(Currency.EUR)
                .build()
    }

    def "Success. 200 from remote server"() {
        when:
        def response = service.createPayment(request)

        then:
        response.statusCode == HttpStatus.OK
    }

    def "Failed. 401 from remote server"() {
        setup:
        def headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        headers.setAccept(List.of(MediaType.APPLICATION_JSON))

        and:
        def httpEntity = new HttpEntity<>(request, headers)

        when:
        restClient.post(httpEntity, PazeResponse.class)

        then:
        HttpClientErrorException ex = thrown()
        ex.message.contains('401')
    }
}

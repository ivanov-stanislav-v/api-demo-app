package eu.paze.isv.service

import eu.paze.isv.CommonSpecification
import eu.paze.isv.service.PaymentService
import eu.paze.isv.service.RestClient
import eu.paze.isv.service.model.Currency
import eu.paze.isv.service.model.PaymentType
import eu.paze.isv.service.model.PazeRequest
import eu.paze.isv.service.model.PazeResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.client.HttpClientErrorException
import spock.lang.Shared

class RestClientTest extends CommonSpecification {

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

    def "Success. 200 от удаленного сервера"() {
        when:
        def response = service.createPayment(request)

        then:
        response.statusCode == HttpStatus.OK
    }
}

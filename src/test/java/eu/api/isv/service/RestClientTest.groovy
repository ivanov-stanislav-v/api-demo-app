package eu.api.isv.service

import eu.api.isv.CommonSpecification
import eu.api.isv.service.model.PaymentType
import eu.api.isv.service.model.PazeRequest
import eu.api.isv.service.model.PazeResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import spock.lang.Shared

class RestClientTest extends CommonSpecification {

    @Autowired
    RestClient restClient

    @Shared
    PazeRequest request

    def setupSpec() {
        request = PazeRequest.builder()
                .amount(0.01)
                .paymentType(PaymentType.DEPOSIT)
                .currency(eu.api.isv.service.model.Currency.EUR)
                .build()
    }

    def "Success. 200 от удаленного сервера"() {
        when:
        def response = restClient.post(request, PazeResponse.class)

        then:
        response.statusCode == HttpStatus.OK
    }
}

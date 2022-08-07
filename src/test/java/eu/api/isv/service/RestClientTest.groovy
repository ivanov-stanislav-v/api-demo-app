package eu.api.isv.service

import eu.api.isv.CommonSpecification
import eu.api.isv.service.model.PaymentType
import eu.api.isv.service.model.ApiRequest
import eu.api.isv.service.model.ApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Title

@Ignore
@Title("Не оставляю в проекте ссылки на реальный тестовый API, заменяю его URL на фиктивный")
class RestClientTest extends CommonSpecification {

    @Autowired
    RestClient restClient

    @Shared
    ApiRequest request

    def setupSpec() {
        request = ApiRequest.builder()
                .amount(0.01)
                .paymentType(PaymentType.DEPOSIT)
                .currency(eu.api.isv.service.model.Currency.EUR)
                .build()
    }

    def "Success. 200 от удаленного сервера"() {
        when:
        def response = restClient.post(request, ApiResponse.class)

        then:
        response.statusCode == HttpStatus.OK
        response.body.getResult().getRedirectUrl().startsWith('https://demo.')
    }
}

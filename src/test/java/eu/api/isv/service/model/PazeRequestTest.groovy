package eu.api.isv.service.model

import com.fasterxml.jackson.databind.ObjectMapper
import eu.api.isv.CommonSpecification
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Shared
import spock.lang.Unroll

import javax.validation.Validator

class PazeRequestTest extends CommonSpecification {

    @Shared
    PazeRequest request

    @Autowired
    ObjectMapper mapper

    @Autowired
    Validator validator

    @Unroll
    def "Success. Сериализация"() {
        setup:
        request = PazeRequest.builder()
                .amount(amount as BigDecimal)
                .build()

        expect:
        mapper.writeValueAsString(request) == """{"paymentType":"DEPOSIT","amount":$amount,"currency":"EUR"}"""

        where:
        amount << [null, 1.0, 5, -5.6, 13.15]
    }

    @Unroll
    def "Success. Десериализация"() {
        setup:
        request = mapper.readValue(json, PazeRequest.class)

        expect:
        request.amount == amount
        request.currency == Currency.EUR
        request.paymentType == PaymentType.DEPOSIT

        where:
        json                   | amount
        "{}"                   | null
        '{"amount":0}'         | 0
        '{"amount":-56.16954}' | -56.16954
        '{"amount":null}'      | null
        '{"amount":123}'       | 123
        '{"amount":0.0001}'    | 0.0001
    }

    @Unroll
    def "Success. Успешная валидация с amount=#amount"() {
        setup:
        request = PazeRequest.builder()
                .amount(amount)
                .build()

        expect:
        validator.validate(request).isEmpty()

        where:
        amount << [0.01, 999999.99]
    }

    def "Failed. Ломаемся на валидации с amount=#amount"() {
        setup:
        request = PazeRequest.builder()
                .amount(amount)
                .build()

        when:
        def errors = validator.validate(request)

        then:
        errors.first().messageTemplate == template

        where:
        amount     | template
        -0.01      | '{javax.validation.constraints.Positive.message}'
        0.0        | '{javax.validation.constraints.Positive.message}'
        0.001      | '{javax.validation.constraints.Digits.message}'
        9999999.99 | '{javax.validation.constraints.Digits.message}'
    }
}

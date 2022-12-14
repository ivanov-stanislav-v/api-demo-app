package eu.api.isv.service

import eu.api.isv.CommonSpecification
import eu.api.isv.service.model.ApiResponse
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
class PaymentControllerTest extends CommonSpecification {

    @Autowired
    MockMvc mockMvc

    @MockBean
    RestClient client

    def setup() {
        Mockito.when(client.post(Mockito.any(), Mockito.eq(ApiResponse.class)))
                .thenReturn(new ResponseEntity(new ApiResponse(200, null), HttpStatus.OK))
    }

    private String post(String amount, ResultMatcher resStatus = status().isOk()) {
        mockMvc.perform(MockMvcRequestBuilders.post("/payments/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"amount":"${amount}"}""")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(resStatus)
                .andReturn()
                .response
                .contentAsString
    }

    def "Success. Контроллер успешно работает"() {
        when:
        def res = post("0.1")

        then:
        res.contains("200")
    }

    def "Failed. amount не число"() {
        when:
        def res = post("amount", status().isBadRequest())

        then:
        res.contains("JSON parse error")
    }

    def "Failed. Невалидный amount"() {
        when:
        def res = post("-1.2", status().isBadRequest())

        then:
        res.contains("must be greater than 0")
    }
}

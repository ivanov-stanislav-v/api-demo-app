package eu.paze.isv.service

import eu.paze.isv.CommonSpecification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@TestPropertySource(properties = "app.token=badToken")
class AuthTest extends CommonSpecification {

    @Autowired
    MockMvc mockMvc

    private String post(String amount, ResultMatcher resStatus = status().isUnauthorized()) {
        mockMvc.perform(MockMvcRequestBuilders.post("/payments/${amount}")
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
        res.contains("401")
    }
}

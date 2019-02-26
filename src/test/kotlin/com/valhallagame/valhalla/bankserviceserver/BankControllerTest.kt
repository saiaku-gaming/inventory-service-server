package com.valhallagame.valhalla.bankserviceserver

import com.fasterxml.jackson.databind.ObjectMapper
import com.valhallagame.valhalla.bankserviceserver.controller.BankController
import com.valhallagame.valhalla.bankserviceserver.service.BankItemService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc

@RunWith(SpringRunner::class)
@WebMvcTest(BankController::class)
@ActiveProfiles("test")
class BankControllerTest {
    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var bankItemService: BankItemService

    private val objectMapper = ObjectMapper()

    @Test
    fun addCurrency() {
//        val input = AddCurrencyParameter("nisse", CurrencyType.GOLD, 10)
//
//        `when`(currencyService.addCurrency(input.characterName, input.currencyType, input.amount)).thenReturn(Currency(1, input.characterName, input.currencyType, input.amount))
//
//        val result = mvc.perform(post("/v1/currency/add-currency")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(input)))
//                .andExpect(status().isOk)
//                .andReturn()
//
//        val returnCurrency = objectMapper.readValue(result.response.contentAsString, Currency::class.java)
//
//        assertEquals(1L, returnCurrency.id)
//        assertEquals(input.characterName, returnCurrency.characterName)
//        assertEquals(input.currencyType, returnCurrency.type)
//        assertEquals(input.amount, returnCurrency.amount)
    }
}
package com.valhallagame.valhalla.bankserviceserver

import com.valhallagame.valhalla.bankserviceserver.repository.BankItemRepository
import com.valhallagame.valhalla.bankserviceserver.service.BankItemService
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@ActiveProfiles("test")
class BankServiceTest {
    @TestConfiguration
    class BankItemServiceTestContextConfiguration {
        @Bean
        fun bankItemService(): BankItemService {
            return BankItemService()
        }
    }

    @Autowired
    private lateinit var bankItemService: BankItemService

    @MockBean
    private lateinit var bankItemRepository: BankItemRepository
}

package com.valhallagame.valhalla.bankserviceserver

import com.valhallagame.valhalla.inventoryserviceserver.repository.InventoryItemRepository
import com.valhallagame.valhalla.inventoryserviceserver.service.InventoryItemService
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@ActiveProfiles("test")
class InventoryServiceTest {
    @TestConfiguration
    class BankItemServiceTestContextConfiguration {
        @Bean
        fun bankItemService(): InventoryItemService {
            return InventoryItemService()
        }
    }

    @Autowired
    private lateinit var bankItemService: InventoryItemService

    @MockBean
    private lateinit var bankItemRepository: InventoryItemRepository
}

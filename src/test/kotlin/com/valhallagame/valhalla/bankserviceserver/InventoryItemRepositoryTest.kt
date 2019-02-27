package com.valhallagame.valhalla.bankserviceserver

import com.valhallagame.valhalla.inventoryserviceserver.model.InventoryItem
import com.valhallagame.valhalla.inventoryserviceserver.repository.InventoryItemRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
@ActiveProfiles("test")
class InventoryItemRepositoryTest {

    @Autowired
    private lateinit var entityManager: TestEntityManager

    @Autowired
    private lateinit var inventoryItemRepository: InventoryItemRepository

    @Test
    fun deleteBankItemByCharacterName() {
        val bankItem1 = InventoryItem(characterName = "nisse", itemName = "sword", positionX = 0, positionY = 0)
        val bankItem2 = InventoryItem(characterName = "hult", itemName = "sword", positionX = 0, positionY = 0)
        val bankItem3 = InventoryItem(characterName = "nisse", itemName = "apple", positionX = 0, positionY = 1)
        val bankItem4 = InventoryItem(characterName = "nisse", itemName = "bow", positionX = 0, positionY = 2)
        val bankItem5 = InventoryItem(characterName = "lisa", itemName = "sword", positionX = 0, positionY = 0)

        entityManager.persist(bankItem1)
        entityManager.persist(bankItem2)
        entityManager.persist(bankItem3)
        entityManager.persist(bankItem4)
        entityManager.persist(bankItem5)
        entityManager.flush()

        val deletedRows = inventoryItemRepository.deleteInventoryItemByCharacterName("nisse")

        assertEquals(3, deletedRows)

        val items = inventoryItemRepository.findAll()

        assertEquals(2, items.size)
        assertFalse(items.any { it.characterName == "nisse" })
    }

    @Test
    fun deleteBankItemByPosition() {
        val bankItem1 = InventoryItem(characterName = "nisse", itemName = "sword", positionX = 0, positionY = 0)
        val bankItem2 = InventoryItem(characterName = "hult", itemName = "sword", positionX = 0, positionY = 0)
        val bankItem3 = InventoryItem(characterName = "nisse", itemName = "apple", positionX = 0, positionY = 1)
        val bankItem4 = InventoryItem(characterName = "nisse", itemName = "bow", positionX = 0, positionY = 2)
        val bankItem5 = InventoryItem(characterName = "lisa", itemName = "sword", positionX = 0, positionY = 0)

        entityManager.persist(bankItem1)
        entityManager.persist(bankItem2)
        entityManager.persist(bankItem3)
        entityManager.persist(bankItem4)
        entityManager.persist(bankItem5)
        entityManager.flush()

        val deletedRows = inventoryItemRepository.deleteInventoryItemByPosition("nisse", 0, 1)

        assertEquals(1, deletedRows)

        val items = inventoryItemRepository.findAll()

        assertEquals(4, items.size)
    }
}
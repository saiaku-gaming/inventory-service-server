package com.valhallagame.valhalla.bankserviceserver

import com.valhallagame.valhalla.bankserviceserver.model.BankItem
import com.valhallagame.valhalla.bankserviceserver.repository.BankItemRepository
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
class BankItemRepositoryTest {

    @Autowired
    private lateinit var entityManager: TestEntityManager

    @Autowired
    private lateinit var bankItemRepository: BankItemRepository

    @Test
    fun deleteBankItemByCharacterName() {
        val bankItem1 = BankItem(characterName = "nisse", itemName = "sword", positionX = 0, positionY = 0)
        val bankItem2 = BankItem(characterName = "hult", itemName = "sword", positionX = 0, positionY = 0)
        val bankItem3 = BankItem(characterName = "nisse", itemName = "apple", positionX = 0, positionY = 1)
        val bankItem4 = BankItem(characterName = "nisse", itemName = "bow", positionX = 0, positionY = 2)
        val bankItem5 = BankItem(characterName = "lisa", itemName = "sword", positionX = 0, positionY = 0)

        entityManager.persist(bankItem1)
        entityManager.persist(bankItem2)
        entityManager.persist(bankItem3)
        entityManager.persist(bankItem4)
        entityManager.persist(bankItem5)
        entityManager.flush()

        val deletedRows = bankItemRepository.deleteBankItemByCharacterName("nisse")

        assertEquals(3, deletedRows)

        val items = bankItemRepository.findAll()

        assertEquals(2, items.size)
        assertFalse(items.any { it.characterName == "nisse" })
    }

    @Test
    fun deleteBankItemByPosition() {
        val bankItem1 = BankItem(characterName = "nisse", itemName = "sword", positionX = 0, positionY = 0)
        val bankItem2 = BankItem(characterName = "hult", itemName = "sword", positionX = 0, positionY = 0)
        val bankItem3 = BankItem(characterName = "nisse", itemName = "apple", positionX = 0, positionY = 1)
        val bankItem4 = BankItem(characterName = "nisse", itemName = "bow", positionX = 0, positionY = 2)
        val bankItem5 = BankItem(characterName = "lisa", itemName = "sword", positionX = 0, positionY = 0)

        entityManager.persist(bankItem1)
        entityManager.persist(bankItem2)
        entityManager.persist(bankItem3)
        entityManager.persist(bankItem4)
        entityManager.persist(bankItem5)
        entityManager.flush()

        val deletedRows = bankItemRepository.deleteBankItemByPosition("nisse", 0, 1)

        assertEquals(1, deletedRows)

        val items = bankItemRepository.findAll()

        assertEquals(4, items.size)
    }
}
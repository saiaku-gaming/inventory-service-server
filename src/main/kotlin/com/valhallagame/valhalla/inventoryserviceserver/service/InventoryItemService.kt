package com.valhallagame.valhalla.inventoryserviceserver.service

import com.valhallagame.valhalla.inventoryserviceserver.model.InventoryItem
import com.valhallagame.valhalla.inventoryserviceserver.repository.InventoryItemRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class InventoryItemService {
    companion object {
        private val logger = LoggerFactory.getLogger(InventoryItemService::class.java)
    }

    @Autowired
    private lateinit var inventoryItemRepository: InventoryItemRepository

    fun deleteInventoryItemByCharacterName(characterName: String): Int {
        logger.info("Deleting all inventory items for {}", characterName)
        return inventoryItemRepository.deleteInventoryItemByCharacterName(characterName)
    }

    fun getInventoryItems(characterName: String): List<InventoryItem> {
        logger.info("Getting inventory items for {}", characterName)
        val bankItems = inventoryItemRepository.getInventoryItemsByCharacterName(characterName)
        logger.info("Inventory items gotten: {}", bankItems)
        return bankItems
    }

    fun setInventoryContents(characterName: String, inventoryItems: List<BankItemWrapper>): List<InventoryItem> {
        logger.info("Setting inventory content for {} to {}", characterName, inventoryItems)
        inventoryItemRepository.deleteInventoryItemByCharacterName(characterName)

        return inventoryItems.map {
            createInventoryItem(characterName, it.itemName, it.positionX, it.positionY, it.itemMetaData)
        }
    }

    fun createInventoryItem(characterName: String, itemName: String, positionX: Int, positionY: Int, itemMetaData: String?): InventoryItem {
        logger.info("Creating inventory item for {} with name {} and position X: {}, Y: {}, Item Meta Data: {}", characterName, itemName, positionX, positionY, itemMetaData)

        deleteInventoryItemByPosition(characterName, positionX, positionY)

        return inventoryItemRepository.save(
                InventoryItem(
                        characterName = characterName,
                        itemName = itemName,
                        positionX = positionX,
                        positionY = positionY,
                        itemMetaData = itemMetaData
                )
        )
    }

    fun deleteInventoryItemByPosition(characterName: String, positionX: Int, positionY: Int): Int {
        logger.info("Deleting inventory item for {} on position X: {}, Y: {}", characterName, positionX, positionY)
        return inventoryItemRepository.deleteInventoryItemByPosition(characterName, positionX, positionY)
    }

    data class BankItemWrapper(
        val itemName: String,
        val positionX: Int,
        val positionY: Int,
        val itemMetaData: String?
    )
}
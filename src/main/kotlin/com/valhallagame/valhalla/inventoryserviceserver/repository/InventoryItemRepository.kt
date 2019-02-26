package com.valhallagame.valhalla.inventoryserviceserver.repository

import com.valhallagame.valhalla.inventoryserviceserver.model.InventoryItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import javax.transaction.Transactional

interface InventoryItemRepository: JpaRepository<InventoryItem, Long> {
    @Modifying
    @Transactional
    fun deleteInventoryItemByCharacterName(characterName: String): Int

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM inventory_item WHERE character_name = :characterName AND position_x = :positionX AND position_y = :positionY", nativeQuery = true)
    fun deleteInventoryItemByPosition(@Param("characterName") characterName: String, @Param("positionX") positionX: Int, @Param("positionY") positionY: Int): Int

    fun getInventoryItemsByCharacterName(characterName: String): List<InventoryItem>
}
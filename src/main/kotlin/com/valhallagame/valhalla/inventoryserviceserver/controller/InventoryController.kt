package com.valhallagame.valhalla.inventoryserviceserver.controller

import com.fasterxml.jackson.databind.JsonNode
import com.valhallagame.common.JS
import com.valhallagame.inventoryserviceclient.message.AddInventoryItemParameter
import com.valhallagame.inventoryserviceclient.message.DeleteInventoryItemParameter
import com.valhallagame.inventoryserviceclient.message.GetInventoryItemsParameter
import com.valhallagame.inventoryserviceclient.message.SetInventoryItemContentsParameter
import com.valhallagame.valhalla.inventoryserviceserver.service.InventoryItemService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import javax.validation.Valid

@Controller
@RequestMapping(path = ["/v1/inventory"])
class InventoryController {
    companion object {
        private val logger = LoggerFactory.getLogger(InventoryController::class.java)
    }

    @Autowired
    private lateinit var inventoryItemService: InventoryItemService

    @PostMapping("/get-inventory-items")
    @ResponseBody
    fun getBankItems(@Valid @RequestBody input: GetInventoryItemsParameter): ResponseEntity<JsonNode> {
        logger.info("Get Inventory Item called with {}", input)
        return JS.message(HttpStatus.OK, inventoryItemService.getInventoryItems(input.characterName))
    }

    @PostMapping("/add-inventory-item")
    @ResponseBody
    fun addBankItem(@Valid @RequestBody input: AddInventoryItemParameter): ResponseEntity<JsonNode> {
        logger.info("Add Inventory Item called with {}", input)
        return JS.message(HttpStatus.OK, inventoryItemService.createInventoryItem(input.characterName, input.itemName, input.positionX, input.positionY))
    }

    @PostMapping("delete-inventory-item")
    @ResponseBody
    fun deleteBankItem(@Valid @RequestBody input: DeleteInventoryItemParameter): ResponseEntity<JsonNode> {
        logger.info("Delete Inventory Item called with {}", input)
        return JS.message(HttpStatus.OK, "Deleted ${inventoryItemService.deleteInventoryItemByPosition(input.characterName, input.positionX, input.positionY)} items")
    }

    @PostMapping("/set-inventory-contents")
    @ResponseBody
    fun setBankContents(@Valid @RequestBody input: SetInventoryItemContentsParameter): ResponseEntity<JsonNode> {
        logger.info("Set Inventory Item called with {}", input)
        return JS.message(HttpStatus.OK, inventoryItemService.setInventoryContents(input.characterName, input.items.map {
            InventoryItemService.BankItemWrapper(it.itemName, it.positionX, it.positionY)
        }))
    }
}
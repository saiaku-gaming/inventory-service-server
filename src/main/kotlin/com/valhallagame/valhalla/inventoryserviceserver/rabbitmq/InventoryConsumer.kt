package com.valhallagame.valhalla.inventoryserviceserver.rabbitmq

import com.valhallagame.common.rabbitmq.NotificationMessage
import com.valhallagame.valhalla.inventoryserviceserver.service.InventoryItemService
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class InventoryConsumer
    @Autowired
    constructor(
            private val inventoryItemService: InventoryItemService
    ){

    companion object {
        private val logger = LoggerFactory.getLogger(InventoryConsumer::class.java)
    }

    @RabbitListener(queues = ["#{inventoryCharacterDeleteQueue.name}"])
    fun receivedCharacterDeleteNotification(notificationMessage: NotificationMessage) {
        logger.info("Received Character Delete Notification: {}", notificationMessage)

        val characterName = notificationMessage.data["characterName"] as String
        inventoryItemService.deleteInventoryItemByCharacterName(characterName)
    }
}
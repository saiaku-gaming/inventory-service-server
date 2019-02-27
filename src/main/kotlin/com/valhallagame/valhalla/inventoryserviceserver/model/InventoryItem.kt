package com.valhallagame.valhalla.inventoryserviceserver.model

import javax.persistence.*

@Entity
@Table(name = "inventory_item")
data class InventoryItem(
        @Id
        @SequenceGenerator(name = "inventory_item_inventory_item_id_seq", sequenceName = "inventory_item_inventory_item_id_seq", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_item_inventory_item_id_seq")
        @Column(name = "inventory_item_id")
        val id: Long? = null,

        @Column(name = "character_name")
        val characterName: String,

        @Column(name = "item_name")
        val itemName: String,

        @Column(name = "position_x")
        val positionX: Int,

        @Column(name = "position_y")
        val positionY: Int
)
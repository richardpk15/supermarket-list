package com.richardcosta.supermarketlist.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val quantity: Int,
    val isUrgent: Boolean = false,
    val category: String? = null
)

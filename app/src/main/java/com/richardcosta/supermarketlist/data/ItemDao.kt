package com.richardcosta.supermarketlist.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert
    suspend fun insert(item: Item)

    @Query("SELECT * FROM items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>

    @Query("SELECT * FROM items WHERE category = :category")
    fun getItemsByCategory(category: String): Flow<List<Item>>

    @Delete
    suspend fun delete(item: Item)
}
package com.example.database

import androidx.room.Dao
import androidx.room.Query
import com.example.database.entities.CardItemEntity

@Dao
interface CardItemDao {
    @Query("SELECT * FROM card_items")
    suspend fun getAll(): List<CardItemEntity>
}
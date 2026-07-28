package com.example.database

import androidx.room3.Dao
import androidx.room3.Query
import com.example.database.entities.CardItemEntity

@Dao
interface CardItemDao {
    @Query("SELECT * FROM card_items")
    suspend fun getAll(): List<CardItemEntity>
}
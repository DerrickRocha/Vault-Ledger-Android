package com.example.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.database.entities.CardItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CardItemDao {
    @Query("SELECT * FROM card_items")
    suspend fun getAll(): List<CardItemEntity>

    @Query("SELECT * FROM card_items")
    fun getAllByFlow(): Flow<List<CardItemEntity>>

    @Query("SELECT * FROM card_items WHERE id = :id")
    fun getById(id: Int): Flow<CardItemEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cardItem: CardItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cardItems: List<CardItemEntity>)

    @Update
    suspend fun update(cardItem: CardItemEntity)

    @Delete
    suspend fun delete(cardItem: CardItemEntity)

    @Query("DELETE FROM card_items")
    suspend fun deleteAll()
}

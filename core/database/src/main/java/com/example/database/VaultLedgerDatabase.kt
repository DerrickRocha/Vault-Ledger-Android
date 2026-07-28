package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.entities.CardItemEntity

@Database(entities = [CardItemEntity::class], version = 1)
abstract class VaultLedgerDatabase: RoomDatabase() {
    abstract fun cardItemDao(): CardItemDao
}
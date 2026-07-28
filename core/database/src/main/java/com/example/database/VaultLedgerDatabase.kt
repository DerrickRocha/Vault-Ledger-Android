package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.database.dao.CardItemDao
import com.example.database.entities.CardItemEntity
import com.example.database.util.VaultLedgerConverters

@Database(entities = [CardItemEntity::class], version = 1)
@TypeConverters(VaultLedgerConverters::class)
abstract class VaultLedgerDatabase: RoomDatabase() {
    abstract fun cardItemDao(): CardItemDao
}
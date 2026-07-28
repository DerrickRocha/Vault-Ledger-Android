package com.example.database

import androidx.room3.Database
import com.example.database.entities.CardItemEntity

@Database(entities = [CardItemEntity::class], version = 1)
abstract class VaultLedgerDatabase {
    abstract fun cardItemDao(): CardItemDao
}
package com.example.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_items")
data class CardItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    @ColumnInfo(name = "card_set") val cardSet: String,
    @ColumnInfo(name = "grading_condition") val gradingCondition: String,
    val valuation: Double
)